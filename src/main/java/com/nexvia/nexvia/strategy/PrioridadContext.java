package com.nexvia.nexvia.strategy;

import com.nexvia.nexvia.entity.PrioridadTicket;
import com.nexvia.nexvia.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrioridadContext {
    private final List<PrioridadStrategy> estrategias;

    public PrioridadContext(List<PrioridadStrategy> estrategias) {
        this.estrategias = estrategias;
    }

    public PrioridadTicket calcular(Ticket ticket) {
        return estrategias.stream()
                .filter(estrategia -> estrategia.aplica(ticket))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No existe estrategia de prioridad disponible"))
                .calcularPrioridad(ticket);
    }
}
