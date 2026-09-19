package com.nexvia.nexvia.chain;

import com.nexvia.nexvia.entity.Ticket;

public abstract class ManejadorEscalamiento {
    protected ManejadorEscalamiento siguienteManejador;

    public void setSiguienteManejador(ManejadorEscalamiento siguienteManejador) {
        this.siguienteManejador = siguienteManejador;
    }

    public abstract void manejarTicket(Ticket ticket);
}
