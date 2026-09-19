package com.nexvia.nexvia.command;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CommandInvoker {

    private final List<String> historial = new ArrayList<>();

    public void ejecutar(TicketCommand command) {
        command.ejecutar();
        historial.add(LocalDateTime.now() + " | Ejecutado: " + command.getNombre());
    }

    public List<String> obtenerHistorial() {
        return Collections.unmodifiableList(historial);
    }
}
