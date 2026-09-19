package com.nexvia.nexvia.service;

import com.nexvia.nexvia.chain.ManejadorEscalamiento;
import com.nexvia.nexvia.chain.SoporteNivel1;
import com.nexvia.nexvia.chain.SoporteNivel2;
import com.nexvia.nexvia.dto.RegistroTicketRequest;
import com.nexvia.nexvia.dto.TicketResponse;
import com.nexvia.nexvia.entity.Categoria;
import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.Ticket;
import com.nexvia.nexvia.entity.Usuario;
import com.nexvia.nexvia.factory.TicketFactory;
import com.nexvia.nexvia.factory.TicketFactoryProvider;
import com.nexvia.nexvia.repository.CategoriaRepository;
import com.nexvia.nexvia.repository.TicketRepository;
import com.nexvia.nexvia.repository.UsuarioRepository;
import com.nexvia.nexvia.observer.TicketSubject;
import com.nexvia.nexvia.strategy.PrioridadContext;
import com.nexvia.nexvia.state.TicketEstadoContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final TicketFactoryProvider ticketFactoryProvider;
    private final PrioridadContext prioridadContext;
    private final TicketSubject ticketSubject;
    private final TicketEstadoContext ticketEstadoContext;

    public TicketService(TicketRepository ticketRepository,
                         UsuarioRepository usuarioRepository,
                         CategoriaRepository categoriaRepository,
                         TicketFactoryProvider ticketFactoryProvider,
                         PrioridadContext prioridadContext,
                         TicketSubject ticketSubject,
                         TicketEstadoContext ticketEstadoContext) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.ticketFactoryProvider = ticketFactoryProvider;
        this.prioridadContext = prioridadContext;
        this.ticketSubject = ticketSubject;
        this.ticketEstadoContext = ticketEstadoContext;
    }

    @Transactional
    public TicketResponse registrarTicket(RegistroTicketRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("No existe el usuario con id: " + request.getUsuarioId()));

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("No existe la categoría con id: " + request.getCategoriaId()));

        TicketFactory factory = ticketFactoryProvider.obtenerFactory(request.getTipo());
        Ticket ticket = factory.crearTicket(request, usuario, categoria);

        // Se calcula la prioridad automáticamente usando Patrón Strategy
        ticket.setPrioridad(prioridadContext.calcular(ticket));

        // Se guarda el ticket en la base de datos
        Ticket guardado = ticketRepository.save(ticket);

        // EVALUAR ESCALAMIENTO AUTOMÁTICO (Patrón Chain of Responsibility)
        evaluarEscalamiento(guardado);

        return convertirAResponse(guardado);
    }

    // Método para aplicar la Cadena de Responsabilidad
    public void evaluarEscalamiento(Ticket ticket) {
        ManejadorEscalamiento nivel1 = new SoporteNivel1();
        ManejadorEscalamiento nivel2 = new SoporteNivel2();

        // Se configura la cadena: Si Nivel 1 no puede, lo pasa a Nivel 2
        nivel1.setSiguienteManejador(nivel2);

        // Se inicia la evaluación desde el primer eslabón
        nivel1.manejarTicket(ticket);
    }

    @Transactional(readOnly = true)
    public List<TicketResponse> listarTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Transactional
    public TicketResponse cambiarEstado(Long ticketId, EstadoTicket nuevoEstado) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("No existe el ticket con id: " + ticketId));

        EstadoTicket estadoAnterior = ticket.getEstado();

        if (estadoAnterior == nuevoEstado) {
            return convertirAResponse(ticket);
        }

        ticketEstadoContext.validarTransicion(ticket, nuevoEstado);

        ticket.setEstado(nuevoEstado);
        Ticket actualizado = ticketRepository.save(ticket);

        ticketSubject.notificarCambioEstado(actualizado, estadoAnterior, nuevoEstado);

        return convertirAResponse(actualizado);
    }

    private TicketResponse convertirAResponse(Ticket ticket) {
        String usuario = ticket.getUsuario() != null
                ? ticket.getUsuario().getNombres() + " " + ticket.getUsuario().getApellidos()
                : null;

        String responsable = ticket.getResponsable() != null
                ? ticket.getResponsable().getNombre()
                : "Sin asignar";

        String categoria = ticket.getCategoria() != null
                ? ticket.getCategoria().getNombre()
                : null;

        return TicketResponse.builder()
                .id(ticket.getId())
                .codigo(ticket.getCodigo())
                .titulo(ticket.getTitulo())
                .descripcion(ticket.getDescripcion())
                .tipo(ticket.getTipo())
                .prioridad(ticket.getPrioridad())
                .estado(ticket.getEstado())
                .urgencia(ticket.getUrgencia())
                .impacto(ticket.getImpacto())
                .categoria(categoria)
                .usuario(usuario)
                .responsable(responsable)
                .fechaRegistro(ticket.getFechaRegistro())
                .build();
    }
}

