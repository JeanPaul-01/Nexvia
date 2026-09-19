package com.nexvia.nexvia.chain;

import com.nexvia.nexvia.entity.Ticket;

public class SoporteNivel1 extends ManejadorEscalamiento {
    @Override
    public void manejarTicket(Ticket ticket) {
        // El Nivel 1 solo atiende tickets de prioridad BAJA
        if (ticket.getPrioridad() != null && ticket.getPrioridad().name().equalsIgnoreCase("BAJA")) {
            System.out.println("-> [Cadena de Responsabilidad] Ticket ID " + ticket.getId() + " retenido y gestionado por Soporte Nivel 1.");
        } else if (siguienteManejador != null) {
            System.out.println("-> [Cadena de Responsabilidad] Nivel 1 no puede resolver Ticket ID " + ticket.getId() + " (Prioridad " + ticket.getPrioridad().name() + "). Escalando a Nivel 2...");
            siguienteManejador.manejarTicket(ticket);
        }
    }
}
