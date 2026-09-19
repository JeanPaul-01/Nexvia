package com.nexvia.nexvia.dto;

import com.nexvia.nexvia.entity.TipoTicket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroTicketRequest {
    private String titulo;
    private String descripcion;
    private TipoTicket tipo;
    private Integer urgencia;
    private Integer impacto;
    private Long usuarioId;
    private Long categoriaId;
}
