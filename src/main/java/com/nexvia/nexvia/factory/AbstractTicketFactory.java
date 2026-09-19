package com.nexvia.nexvia.factory;

import com.nexvia.nexvia.dto.RegistroTicketRequest;
import com.nexvia.nexvia.entity.Categoria;
import com.nexvia.nexvia.entity.EstadoTicket;
import com.nexvia.nexvia.entity.Ticket;
import com.nexvia.nexvia.entity.TipoTicket;
import com.nexvia.nexvia.entity.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AbstractTicketFactory implements TicketFactory {

    protected Ticket construirTicketBase(RegistroTicketRequest request, Usuario usuario, Categoria categoria, TipoTicket tipo) {
        return Ticket.builder()
                .codigo(generarCodigo(tipo))
                .titulo(request.getTitulo())
                .descripcion(request.getDescripcion())
                .tipo(tipo)
                .estado(EstadoTicket.NUEVO)
                .urgencia(validarRango(request.getUrgencia()))
                .impacto(validarRango(request.getImpacto()))
                .fechaRegistro(LocalDateTime.now())
                .usuario(usuario)
                .categoria(categoria)
                .build();
    }

    private String generarCodigo(TipoTicket tipo) {
        return tipo.name().substring(0, 3) + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private Integer validarRango(Integer valor) {
        if (valor == null) {
            return 1;
        }
        if (valor < 1) {
            return 1;
        }
        return Math.min(valor, 5);
    }
}
