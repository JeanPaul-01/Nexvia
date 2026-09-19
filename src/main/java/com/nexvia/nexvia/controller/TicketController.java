package com.nexvia.nexvia.controller;

import com.nexvia.nexvia.dto.CambiarEstadoRequest;
import com.nexvia.nexvia.dto.RegistroTicketRequest;
import com.nexvia.nexvia.dto.TicketResponse;
import com.nexvia.nexvia.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> registrarTicket(@RequestBody RegistroTicketRequest request) {
        TicketResponse response = ticketService.registrarTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> listarTickets() {
        return ResponseEntity.ok(ticketService.listarTickets());
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<TicketResponse> cambiarEstado(@PathVariable Long id,
                                                        @RequestBody CambiarEstadoRequest request) {
        return ResponseEntity.ok(ticketService.cambiarEstado(id, request.getNuevoEstado()));
    }
}
