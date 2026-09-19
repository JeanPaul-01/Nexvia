package com.nexvia.nexvia.dto;

import com.nexvia.nexvia.entity.EstadoTicket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CambiarEstadoRequest {
    private EstadoTicket nuevoEstado;
}
