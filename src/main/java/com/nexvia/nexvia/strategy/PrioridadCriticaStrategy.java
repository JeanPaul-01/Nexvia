package com.nexvia.nexvia.strategy;

import com.nexvia.nexvia.entity.PrioridadTicket;
import com.nexvia.nexvia.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class PrioridadCriticaStrategy implements PrioridadStrategy {
    @Override
    public boolean aplica(Ticket ticket) {
        return ticket.getUrgencia() >= 5 && ticket.getImpacto() >= 5;
    }

    @Override
    public PrioridadTicket calcularPrioridad(Ticket ticket) {
        return PrioridadTicket.CRITICA;
    }
}
