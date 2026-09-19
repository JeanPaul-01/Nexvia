package com.nexvia.nexvia.factory;

import com.nexvia.nexvia.entity.TipoTicket;
import org.springframework.stereotype.Component;

@Component
public class TicketFactoryProvider {
    private final SoporteTicketFactory soporteFactory;
    private final ReclamoTicketFactory reclamoFactory;
    private final IncidenciaTicketFactory incidenciaFactory;
    private final ConsultaTicketFactory consultaFactory;

    public TicketFactoryProvider(SoporteTicketFactory soporteFactory,
                                 ReclamoTicketFactory reclamoFactory,
                                 IncidenciaTicketFactory incidenciaFactory,
                                 ConsultaTicketFactory consultaFactory) {
        this.soporteFactory = soporteFactory;
        this.reclamoFactory = reclamoFactory;
        this.incidenciaFactory = incidenciaFactory;
        this.consultaFactory = consultaFactory;
    }

    public TicketFactory obtenerFactory(TipoTicket tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de ticket es obligatorio");
        }
        return switch (tipo) {
            case SOPORTE -> soporteFactory;
            case RECLAMO -> reclamoFactory;
            case INCIDENCIA -> incidenciaFactory;
            case CONSULTA -> consultaFactory;
        };
    }
}
