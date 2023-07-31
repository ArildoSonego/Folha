package com.exercicio.folha.controllers;

import com.exercicio.folha.models.EventosModel;

import com.exercicio.folha.services.EventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventosController {
    @Autowired
    private EventosService servicoEventos;

    @GetMapping
    public ResponseEntity <List<EventosModel>> buscaTodosEventos() {
        return new ResponseEntity<>(servicoEventos.buscaTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Object> incluiEvento (@Validated @RequestBody EventosModel evento) {
        servicoEventos.inclui(evento);
        return new ResponseEntity<>("Evento incluído com sucesso", HttpStatus.CREATED);
    }
}
