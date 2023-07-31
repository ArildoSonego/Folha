package com.exercicio.folha.controllers;

import com.exercicio.folha.models.LancamentosModel;
import com.exercicio.folha.models.MovimentoModel;
import com.exercicio.folha.services.LancamentosService;
import com.exercicio.folha.services.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimento")
public class MovimentoController {
    @Autowired
    private MovimentoService servicoMovimento;

    @GetMapping
    public ResponseEntity <List<MovimentoModel>> buscaTodosMovimentos() {
        return new ResponseEntity<>(servicoMovimento.buscaTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Object> incluiMovimento (@Validated @RequestBody MovimentoModel movimento) {
        servicoMovimento.inclui(movimento);
        return new ResponseEntity<>("Movimentação incluída com sucesso", HttpStatus.CREATED);
    }
}
