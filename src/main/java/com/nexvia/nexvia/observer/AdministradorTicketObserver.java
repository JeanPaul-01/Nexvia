package com.nexvia.nexvia.observer;

import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class AdministradorTicketObserver implements TicketObserver {

    @Override
    public void actualizar(Ticket ticket, EstadoTicket estadoAnterior, EstadoTicket estadoNuevo) {
        System.out.println("[Observer Administrador] El ticket " + ticket.getCodigo()
                + " cambió de estado: " + estadoAnterior + " -> " + estadoNuevo);
    }
}
