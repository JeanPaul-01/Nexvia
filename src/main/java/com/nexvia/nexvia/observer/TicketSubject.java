package com.nexvia.nexvia.observer;

import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketSubject {
    private final List<TicketObserver> observers;

    public TicketSubject(List<TicketObserver> observers) {
        this.observers = observers;
    }

    public void notificarCambioEstado(Ticket ticket, EstadoTicket estadoAnterior, EstadoTicket estadoNuevo) {
        observers.forEach(observer -> observer.actualizar(ticket, estadoAnterior, estadoNuevo));
    }
}
