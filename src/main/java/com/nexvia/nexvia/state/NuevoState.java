package com.nexvia.nexvia.state;

import com.nexvia.nexvia.entity.EstadoTicket;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class NuevoState extends AbstractTicketEstadoState {
    @Override
    public EstadoTicket estadoActual() {
        return EstadoTicket.NUEVO;
    }

    @Override
    protected Set<EstadoTicket> estadosPermitidos() {
        return Set.of(EstadoTicket.ASIGNADO, EstadoTicket.ESCALADO, EstadoTicket.CERRADO);
    }
}
