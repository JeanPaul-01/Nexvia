package com.nexvia.nexvia.factory;

import com.nexvia.nexvia.dto.RegistroTicketRequest;
import com.nexvia.nexvia.entity.Categoria;
import com.nexvia.nexvia.entity.Ticket;
import com.nexvia.nexvia.entity.TipoTicket;
import com.nexvia.nexvia.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ConsultaTicketFactory extends AbstractTicketFactory {
    @Override
    public Ticket crearTicket(RegistroTicketRequest request, Usuario usuario, Categoria categoria) {
        return construirTicketBase(request, usuario, categoria, TipoTicket.CONSULTA);
    }
}
