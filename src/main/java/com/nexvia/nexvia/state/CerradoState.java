package com.nexvia.nexvia.state;

import com.nexvia.nexvia.entity.EstadoTicket;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CerradoState extends AbstractTicketEstadoState {
    @Override
    public EstadoTicket estadoActual() {
        return EstadoTicket.CERRADO;
    }

    @Override
    protected Set<EstadoTicket> estadosPermitidos() {
        return Set.of();
    }
}
