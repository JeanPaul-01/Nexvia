package com.nexvia.nexvia.state;

import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.Ticket;

import java.util.Set;

public abstract class AbstractTicketEstadoState implements TicketEstadoState {

    protected abstract Set<EstadoTicket> estadosPermitidos();

    @Override
    public boolean puedeCambiarA(EstadoTicket nuevoEstado) {
        return estadosPermitidos().contains(nuevoEstado);
    }

    @Override
    public void validarCambio(Ticket ticket, EstadoTicket nuevoEstado) {
        if (ticket.getEstado() == nuevoEstado) {
            return;
        }
        if (!puedeCambiarA(nuevoEstado)) {
            throw new IllegalStateException(
                    "Transición no permitida: " + ticket.getEstado() + " -> " + nuevoEstado
            );
        }
    }
}
