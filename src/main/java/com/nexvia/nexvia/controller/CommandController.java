package com.nexvia.nexvia.controller;

import com.nexvia.nexvia.command.CommandInvoker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comandos")
public class CommandController {

    private final CommandInvoker commandInvoker;

    public CommandController(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    @GetMapping("/historial")
    public ResponseEntity<List<String>> historial() {
        return ResponseEntity.ok(commandInvoker.obtenerHistorial());
    }
}
