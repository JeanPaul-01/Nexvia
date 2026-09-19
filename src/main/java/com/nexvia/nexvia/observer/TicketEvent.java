package com.nexvia.nexvia.observer;

import com.nexvia.nexvia.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketEvent {
    private Ticket ticket;
    private String mensaje;
}
