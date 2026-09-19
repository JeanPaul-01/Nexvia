package com.nexvia.nexvia.repository;

import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.PrioridadTicket;
import com.nexvia.nexvia.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEstado(EstadoTicket estado);
    List<Ticket> findByPrioridad(PrioridadTicket prioridad);
    List<Ticket> findByUsuarioId(Long usuarioId);
    List<Ticket> findByResponsableId(Long responsableId);
}
