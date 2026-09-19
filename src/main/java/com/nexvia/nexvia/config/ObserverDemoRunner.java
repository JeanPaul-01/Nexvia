package com.nexvia.nexvia.config;

import com.nexvia.nexvia.dto.RegistroTicketRequest;
import com.nexvia.nexvia.dto.TicketResponse;
import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.Responsable;
import com.nexvia.nexvia.entity.Ticket;
import com.nexvia.nexvia.entity.TipoTicket;
import com.nexvia.nexvia.repository.CategoriaRepository;
import com.nexvia.nexvia.repository.NotificacionRepository;
import com.nexvia.nexvia.repository.ResponsableRepository;
import com.nexvia.nexvia.repository.TicketRepository;
import com.nexvia.nexvia.repository.UsuarioRepository;
import com.nexvia.nexvia.service.TicketService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(2)
public class ObserverDemoRunner implements CommandLineRunner {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ResponsableRepository responsableRepository;
    private final NotificacionRepository notificacionRepository;
    private final TicketService ticketService;

    public ObserverDemoRunner(TicketRepository ticketRepository,
                              UsuarioRepository usuarioRepository,
                              CategoriaRepository categoriaRepository,
                              ResponsableRepository responsableRepository,
                              NotificacionRepository notificacionRepository,
                              TicketService ticketService) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.responsableRepository = responsableRepository;
        this.notificacionRepository = notificacionRepository;
        this.ticketService = ticketService;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Long usuarioId = usuarioRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No hay usuarios para ejecutar la demo"))
                .getId();

        Long categoriaId = categoriaRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No hay categorías para ejecutar la demo"))
                .getId();

        RegistroTicketRequest request = new RegistroTicketRequest();
        request.setTitulo("Demo Observer - acceso al sistema");
        request.setDescripcion("Ticket creado automáticamente para demostrar el patrón Observer.");
        request.setTipo(TipoTicket.INCIDENCIA);
        request.setUrgencia(4);
        request.setImpacto(3);
        request.setUsuarioId(usuarioId);
        request.setCategoriaId(categoriaId);

        TicketResponse creado = ticketService.registrarTicket(request);

        Ticket ticket = ticketRepository.findById(creado.getId())
                .orElseThrow(() -> new IllegalStateException("No se encontró el ticket demo"));

        responsableRepository.findByDisponibleTrue().stream()
                .findFirst()
                .ifPresent(responsable -> asignarResponsable(ticket, responsable));

        System.out.println("========== DEMO OBSERVER INICIADA ==========");
        System.out.println("Ticket demo creado: " + ticket.getCodigo());

        ticketService.cambiarEstado(ticket.getId(), EstadoTicket.ASIGNADO);
        ticketService.cambiarEstado(ticket.getId(), EstadoTicket.EN_PROCESO);
        ticketService.cambiarEstado(ticket.getId(), EstadoTicket.RESUELTO);
        ticketService.cambiarEstado(ticket.getId(), EstadoTicket.CERRADO);

        long totalNotificaciones = notificacionRepository.findByTicketId(ticket.getId()).size();
        System.out.println("Notificaciones guardadas para el ticket demo: " + totalNotificaciones);
        System.out.println("Patrón State validó el flujo: NUEVO -> ASIGNADO -> EN_PROCESO -> RESUELTO -> CERRADO");
        System.out.println("Consulta en navegador: http://localhost:8080/api/notificaciones");
        System.out.println("========== DEMO OBSERVER + STATE FINALIZADA ==========");
    }

    private void asignarResponsable(Ticket ticket, Responsable responsable) {
        ticket.setResponsable(responsable);
        ticketRepository.save(ticket);
    }
}
