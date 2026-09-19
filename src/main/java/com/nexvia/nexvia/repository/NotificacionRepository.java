package com.nexvia.nexvia.repository;

import com.nexvia.nexvia.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUsuarioId(Long usuarioId);
    List<Notificacion> findByTicketId(Long ticketId);
    List<Notificacion> findByUsuarioIdAndLeidaFalse(Long usuarioId);
}
