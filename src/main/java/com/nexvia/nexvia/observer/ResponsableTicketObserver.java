package com.nexvia.nexvia.observer;

import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class ResponsableTicketObserver implements TicketObserver {
    @Override
    public void actualizar(Ticket ticket, EstadoTicket estadoAnterior, EstadoTicket estadoNuevo) {
        if (ticket.getResponsable() != null) {
            System.out.println("[Observer Responsable] El responsable "
                    + ticket.getResponsable().getNombre()
                    + " debe revisar el ticket " + ticket.getCodigo()
                    + ". Estado actual: " + estadoNuevo);
        }
    }
}
