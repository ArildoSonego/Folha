package com.exercicio.folha.controllers;

import com.exercicio.folha.models.LancamentosModel;
import com.exercicio.folha.services.LancamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentosController {
    @Autowired
    private LancamentosService servicoLancamentos;

    @GetMapping
    public ResponseEntity <List<LancamentosModel>> buscaTodosLancamentos() {
        return new ResponseEntity<>(servicoLancamentos.buscaTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Object> incluiLancamento (@Validated @RequestBody LancamentosModel lancamento) {
        servicoLancamentos.inclui(lancamento);
        return new ResponseEntity<>("Lançamento incluído com sucesso", HttpStatus.CREATED);
    }
}
