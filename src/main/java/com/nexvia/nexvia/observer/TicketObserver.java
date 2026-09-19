package com.nexvia.nexvia.observer;

import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.Ticket;

public interface TicketObserver {
    void actualizar(Ticket ticket, EstadoTicket estadoAnterior, EstadoTicket estadoNuevo);
}
