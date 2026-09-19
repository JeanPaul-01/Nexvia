package com.nexvia.nexvia.strategy;

import com.nexvia.nexvia.entity.PrioridadTicket;
import com.nexvia.nexvia.entity.Ticket;

public interface PrioridadStrategy {
    PrioridadTicket calcularPrioridad(Ticket ticket);
    boolean aplica(Ticket ticket);
}
