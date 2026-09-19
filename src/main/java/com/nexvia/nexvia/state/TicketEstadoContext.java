package com.nexvia.nexvia.state;

import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class TicketEstadoContext {
    private final Map<EstadoTicket, TicketEstadoState> estados = new EnumMap<>(EstadoTicket.class);

    public TicketEstadoContext(List<TicketEstadoState> estadosDisponibles) {
        estadosDisponibles.forEach(estado -> estados.put(estado.estadoActual(), estado));
    }

    public void validarTransicion(Ticket ticket, EstadoTicket nuevoEstado) {
        TicketEstadoState estadoActual = estados.get(ticket.getEstado());
        if (estadoActual == null) {
            throw new IllegalStateException("No existe implementación State para: " + ticket.getEstado());
        }
        estadoActual.validarCambio(ticket, nuevoEstado);
    }
}
