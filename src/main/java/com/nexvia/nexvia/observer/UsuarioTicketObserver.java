package com.nexvia.nexvia.observer;

import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.Notificacion;
import com.nexvia.nexvia.entity.Ticket;
import com.nexvia.nexvia.repository.NotificacionRepository;
import org.springframework.stereotype.Component;

@Component
public class UsuarioTicketObserver implements TicketObserver {
    private final NotificacionRepository notificacionRepository;

    public UsuarioTicketObserver(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @Override
    public void actualizar(Ticket ticket, EstadoTicket estadoAnterior, EstadoTicket estadoNuevo) {
        if (ticket.getUsuario() == null) {
            return;
        }

        String mensaje = "Tu ticket " + ticket.getCodigo()
                + " cambió de estado: " + estadoAnterior + " → " + estadoNuevo;

        Notificacion notificacion = Notificacion.builder()
                .mensaje(mensaje)
                .leida(false)
                .ticket(ticket)
                .usuario(ticket.getUsuario())
                .build();

        notificacionRepository.save(notificacion);
    }
}
