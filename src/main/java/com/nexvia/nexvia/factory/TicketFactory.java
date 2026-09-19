package com.nexvia.nexvia.factory;

import com.nexvia.nexvia.dto.RegistroTicketRequest;
import com.nexvia.nexvia.entity.Categoria;
import com.nexvia.nexvia.entity.Ticket;
import com.nexvia.nexvia.entity.Usuario;

public interface TicketFactory {
    Ticket crearTicket(RegistroTicketRequest request, Usuario usuario, Categoria categoria);
}
