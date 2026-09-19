package com.nexvia.nexvia.state;

import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.Ticket;

/**
 * Patrón State: define el comportamiento permitido según el estado actual del ticket.
 */
public interface TicketEstadoState {
    EstadoTicket estadoActual();
    boolean puedeCambiarA(EstadoTicket nuevoEstado);
    void validarCambio(Ticket ticket, EstadoTicket nuevoEstado);
}
