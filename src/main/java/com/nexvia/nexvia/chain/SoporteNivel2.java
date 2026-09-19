package com.nexvia.nexvia.chain;

import com.nexvia.nexvia.entity.Ticket;

public class SoporteNivel2 extends ManejadorEscalamiento {
    @Override
    public void manejarTicket(Ticket ticket) {
        // El Nivel 2 atiende prioridades MEDIA, ALTA y CRITICA
        if (ticket.getPrioridad() != null && !ticket.getPrioridad().name().equalsIgnoreCase("BAJA")) {
            System.out.println("-> [Cadena de Responsabilidad] Ticket ID " + ticket.getId() + " escalado exitosamente. Asignado a Soporte Nivel 2.");
        } else if (siguienteManejador != null) {
            System.out.println("-> [Cadena de Responsabilidad] Escalando a Gerencia...");
            siguienteManejador.manejarTicket(ticket);
        }
    }
}
