package com.nexvia.nexvia.config;

import com.nexvia.nexvia.command.CambiarEstadoTicketCommand;
import com.nexvia.nexvia.command.CommandInvoker;
import com.nexvia.nexvia.command.RegistrarTicketCommand;
import com.nexvia.nexvia.dto.RegistroTicketRequest;
import com.nexvia.nexvia.dto.TicketResponse;
import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.TipoTicket;
import com.nexvia.nexvia.repository.CategoriaRepository;
import com.nexvia.nexvia.repository.NotificacionRepository;
import com.nexvia.nexvia.repository.UsuarioRepository;
import com.nexvia.nexvia.service.TicketService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(3)
public class CommandDemoRunner implements CommandLineRunner {

    private final CommandInvoker commandInvoker;
    private final TicketService ticketService;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final NotificacionRepository notificacionRepository;

    public CommandDemoRunner(CommandInvoker commandInvoker,
                             TicketService ticketService,
                             UsuarioRepository usuarioRepository,
                             CategoriaRepository categoriaRepository,
                             NotificacionRepository notificacionRepository) {
        this.commandInvoker = commandInvoker;
        this.ticketService = ticketService;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.notificacionRepository = notificacionRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Long usuarioId = usuarioRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No hay usuarios para ejecutar la demo Command"))
                .getId();

        Long categoriaId = categoriaRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No hay categorías para ejecutar la demo Command"))
                .getId();

        RegistroTicketRequest request = new RegistroTicketRequest();
        request.setTitulo("Demo Command - atención de ticket");
        request.setDescripcion("Ticket creado automáticamente para demostrar el patrón Command.");
        request.setTipo(TipoTicket.RECLAMO);
        request.setUrgencia(5);
        request.setImpacto(4);
        request.setUsuarioId(usuarioId);
        request.setCategoriaId(categoriaId);

        System.out.println("========== DEMO COMMAND INICIADA ==========");

        RegistrarTicketCommand registrarCommand = new RegistrarTicketCommand(ticketService, request);
        commandInvoker.ejecutar(registrarCommand);

        TicketResponse ticketCreado = registrarCommand.getTicketCreado();
        Long ticketId = ticketCreado.getId();

        commandInvoker.ejecutar(new CambiarEstadoTicketCommand(ticketService, ticketId, EstadoTicket.ASIGNADO));
        commandInvoker.ejecutar(new CambiarEstadoTicketCommand(ticketService, ticketId, EstadoTicket.EN_PROCESO));
        commandInvoker.ejecutar(new CambiarEstadoTicketCommand(ticketService, ticketId, EstadoTicket.RESUELTO));
        commandInvoker.ejecutar(new CambiarEstadoTicketCommand(ticketService, ticketId, EstadoTicket.CERRADO));

        long totalNotificaciones = notificacionRepository.findByTicketId(ticketId).size();

        System.out.println();
        System.out.println("========== DEMO COMMAND ==========");
        System.out.println();

        System.out.println("✓ Ejecutando comando RegistrarTicket");
        System.out.println("✓ Ejecutando comando CambiarEstado -> ASIGNADO");
        System.out.println("✓ Ejecutando comando CambiarEstado -> EN_PROCESO");
        System.out.println("✓ Ejecutando comando CambiarEstado -> RESUELTO");
        System.out.println("✓ Ejecutando comando CambiarEstado -> CERRADO");

        System.out.println();
        System.out.println("Historial de comandos:");

        int i = 1;
        for (String comando : commandInvoker.obtenerHistorial()) {
            String limpio = comando;
            int idx = limpio.indexOf("|");
            if (idx >= 0) {
                limpio = limpio.substring(idx + 1).trim();
            }
            limpio = limpio.replace("Ejecutado: ", "");
            limpio = limpio.replace("Registrar ticket", "RegistrarTicket");
            limpio = limpio.replace("Cambiar estado a ", "CambiarEstado -> ");
            System.out.println(i++ + ". " + limpio);
        }

        System.out.println();
        System.out.println("Notificaciones generadas: " + totalNotificaciones);

        System.out.println();
        System.out.println("Historial:");
        System.out.println("http://localhost:8080/api/comandos/historial");

        System.out.println();
        System.out.println("Notificaciones:");
        System.out.println("http://localhost:8080/api/notificaciones");

        System.out.println();
        System.out.println("========== FIN COMMAND ==========");
    }
}
