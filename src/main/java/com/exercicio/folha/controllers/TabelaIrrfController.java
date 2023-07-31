package com.exercicio.folha.controllers;

import com.exercicio.folha.models.TabelaIrrfModel;
import com.exercicio.folha.services.TabelaIrrfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tabelairrf")
public class TabelaIrrfController {
    @Autowired
    private TabelaIrrfService servicoTabelaIrrf;

    @GetMapping
    public ResponseEntity <List<TabelaIrrfModel>> buscaTodosTabelaIrrf() {
        return new ResponseEntity<>(servicoTabelaIrrf.buscaTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Object> incluiTabelaIrrf (@Validated @RequestBody TabelaIrrfModel tabelaIrrf) {
        servicoTabelaIrrf.inclui(tabelaIrrf);
        return new ResponseEntity<>("Item incluído com sucesso na tabela de cálculo do Imposto de Renda", HttpStatus.CREATED);
    }
}
