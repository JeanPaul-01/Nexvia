package com.nexvia.nexvia.state;

import com.nexvia.nexvia.entity.EstadoTicket;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AsignadoState extends AbstractTicketEstadoState {
    @Override
    public EstadoTicket estadoActual() {
        return EstadoTicket.ASIGNADO;
    }

    @Override
    protected Set<EstadoTicket> estadosPermitidos() {
        return Set.of(EstadoTicket.EN_PROCESO, EstadoTicket.ESCALADO, EstadoTicket.CERRADO);
    }
}
