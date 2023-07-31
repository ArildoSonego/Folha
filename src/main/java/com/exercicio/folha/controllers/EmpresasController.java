package com.exercicio.folha.controllers;

import com.exercicio.folha.models.EmpresasModel;
import com.exercicio.folha.services.EmpresasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresasController {
    @Autowired
    private EmpresasService servicoEmpresas;

    @GetMapping
    public ResponseEntity <List<EmpresasModel>> buscaTodasEmpresas() {
        return new ResponseEntity<>(servicoEmpresas.buscaTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Object> incluiEmpresa (@Validated @RequestBody EmpresasModel empresa) {
        servicoEmpresas.inclui(empresa);
        return new ResponseEntity<>("Empresa criada com sucesso", HttpStatus.CREATED);
    }
}
