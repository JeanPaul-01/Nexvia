package com.nexvia.nexvia.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificacionResponse {
    private Long id;
    private String mensaje;
    private LocalDateTime fechaEnvio;
    private boolean leida;
    private Long ticketId;
    private String codigoTicket;
    private String usuario;
}
