package com.nexvia.nexvia.command;

import com.nexvia.nexvia.dto.RegistroTicketRequest;
import com.nexvia.nexvia.dto.TicketResponse;
import com.nexvia.nexvia.service.TicketService;

public class RegistrarTicketCommand implements TicketCommand {

    private final TicketService ticketService;
    private final RegistroTicketRequest request;
    private TicketResponse ticketCreado;

    public RegistrarTicketCommand(TicketService ticketService, RegistroTicketRequest request) {
        this.ticketService = ticketService;
        this.request = request;
    }

    @Override
    public String getNombre() {
        return "Registrar ticket";
    }

    @Override
    public void ejecutar() {
        ticketCreado = ticketService.registrarTicket(request);
        System.out.println("[COMMAND] Ticket registrado: " + ticketCreado.getCodigo());
    }

    public TicketResponse getTicketCreado() {
        return ticketCreado;
    }
}
