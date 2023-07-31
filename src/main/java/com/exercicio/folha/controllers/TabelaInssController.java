package com.exercicio.folha.controllers;

import com.exercicio.folha.models.TabelaInssModel;
import com.exercicio.folha.services.TabelaInssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tabelainss")
public class TabelaInssController {
    @Autowired
    private TabelaInssService servicoTabelaInss;

    @GetMapping
    public ResponseEntity <List<TabelaInssModel>> buscaTodosTabelaInss() {
        return new ResponseEntity<>(servicoTabelaInss.buscaTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Object> incluiTabelaInss (@Validated @RequestBody TabelaInssModel tabelaInss) {
        servicoTabelaInss.inclui(tabelaInss);
        return new ResponseEntity<>("Item incluído com sucesso na tabela de cálculo do INSS", HttpStatus.CREATED);
    }
}
