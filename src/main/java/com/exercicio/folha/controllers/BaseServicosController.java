package com.exercicio.folha.controllers;

import com.exercicio.folha.models.BaseServicosModel;
import com.exercicio.folha.models.EventosModel;
import com.exercicio.folha.services.BaseServicosService;
import com.exercicio.folha.services.EventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baseservicos")
public class BaseServicosController {
    @Autowired
    private BaseServicosService servicoBaseServicos;

    @GetMapping
    public ResponseEntity <List<BaseServicosModel>> buscaTodosBaseServicos() {
        return new ResponseEntity<>(servicoBaseServicos.buscaTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Object> incluiBaseServico (@Validated @RequestBody BaseServicosModel baseservico) {
        servicoBaseServicos.inclui(baseservico);
        return new ResponseEntity<>("Base de serviço incluída com sucesso", HttpStatus.CREATED);
    }
}
