package com.nexvia.nexvia.service;

import com.nexvia.nexvia.dto.NotificacionResponse;
import com.nexvia.nexvia.entity.Notificacion;
import com.nexvia.nexvia.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificacionService {
    private final NotificacionRepository notificacionRepository;

    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }


    @Transactional(readOnly = true)
    public List<NotificacionResponse> listarTodas() {
        return notificacionRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<NotificacionResponse> listarPorTicket(Long ticketId) {
        return notificacionRepository.findByTicketId(ticketId)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<NotificacionResponse> listarPorUsuario(Long usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Transactional
    public NotificacionResponse marcarComoLeida(Long id) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe la notificación con id: " + id));
        notificacion.setLeida(true);
        return convertirAResponse(notificacionRepository.save(notificacion));
    }

    private NotificacionResponse convertirAResponse(Notificacion notificacion) {
        String usuario = notificacion.getUsuario() != null
                ? notificacion.getUsuario().getNombres() + " " + notificacion.getUsuario().getApellidos()
                : null;

        Long ticketId = notificacion.getTicket() != null ? notificacion.getTicket().getId() : null;
        String codigoTicket = notificacion.getTicket() != null ? notificacion.getTicket().getCodigo() : null;

        return NotificacionResponse.builder()
                .id(notificacion.getId())
                .mensaje(notificacion.getMensaje())
                .fechaEnvio(notificacion.getFechaEnvio())
                .leida(notificacion.isLeida())
                .ticketId(ticketId)
                .codigoTicket(codigoTicket)
                .usuario(usuario)
                .build();
    }
}
