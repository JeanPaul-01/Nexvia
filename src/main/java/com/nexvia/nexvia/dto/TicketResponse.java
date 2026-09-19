package com.nexvia.nexvia.dto;

import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.PrioridadTicket;
import com.nexvia.nexvia.entity.TipoTicket;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TicketResponse {
    private Long id;
    private String codigo;
    private String titulo;
    private String descripcion;
    private TipoTicket tipo;
    private PrioridadTicket prioridad;
    private EstadoTicket estado;
    private Integer urgencia;
    private Integer impacto;
    private String categoria;
    private String usuario;
    private String responsable;
    private LocalDateTime fechaRegistro;
}
