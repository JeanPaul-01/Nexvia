package com.nexvia.nexvia.state;

import com.nexvia.nexvia.entity.EstadoTicket;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EnProcesoState extends AbstractTicketEstadoState {
    @Override
    public EstadoTicket estadoActual() {
        return EstadoTicket.EN_PROCESO;
    }

    @Override
    protected Set<EstadoTicket> estadosPermitidos() {
        return Set.of(EstadoTicket.RESUELTO, EstadoTicket.ESCALADO);
    }
}
