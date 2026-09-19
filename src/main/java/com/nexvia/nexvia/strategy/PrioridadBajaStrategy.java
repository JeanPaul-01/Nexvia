package com.nexvia.nexvia.strategy;

import com.nexvia.nexvia.entity.PrioridadTicket;
import com.nexvia.nexvia.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class PrioridadBajaStrategy implements PrioridadStrategy {
    @Override
    public boolean aplica(Ticket ticket) {
        return true;
    }

    @Override
    public PrioridadTicket calcularPrioridad(Ticket ticket) {
        return PrioridadTicket.BAJA;
    }
}
