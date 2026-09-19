package com.nexvia.nexvia.command;

import com.nexvia.nexvia.dto.TicketResponse;
import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.service.TicketService;

public class CambiarEstadoTicketCommand implements TicketCommand {

    private final TicketService ticketService;
    private final Long ticketId;
    private final EstadoTicket nuevoEstado;
    private TicketResponse ticketActualizado;

    public CambiarEstadoTicketCommand(TicketService ticketService, Long ticketId, EstadoTicket nuevoEstado) {
        this.ticketService = ticketService;
        this.ticketId = ticketId;
        this.nuevoEstado = nuevoEstado;
    }

    @Override
    public String getNombre() {
        return "Cambiar estado a " + nuevoEstado;
    }

    @Override
    public void ejecutar() {
        ticketActualizado = ticketService.cambiarEstado(ticketId, nuevoEstado);
        System.out.println("[COMMAND] Ticket " + ticketActualizado.getCodigo() + " cambió a " + nuevoEstado);
    }

    public TicketResponse getTicketActualizado() {
        return ticketActualizado;
    }
}
