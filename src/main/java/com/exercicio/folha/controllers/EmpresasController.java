package com.exercicio.folha.controllers;

import com.exercicio.folha.models.EmpresasModel;
import com.exercicio.folha.services.EmpresasService;
import org.jetbrains.annotations.NotNull;
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

    @GetMapping (value = "/{empresaID}")
    public ResponseEntity <Object> buscaEmpresa (@PathVariable @NotNull Integer empresaID) {
        if (servicoEmpresas.existe(empresaID))
            return new ResponseEntity<>(servicoEmpresas.buscaUm(empresaID),HttpStatus.OK);
        else
            return new ResponseEntity<>("Empresa não cadastrada.",HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity <Object> incluiEmpresa (@Validated @RequestBody @NotNull EmpresasModel empresa) {
        servicoEmpresas.inclui(empresa);
        return new ResponseEntity<>("Empresa cadastrada com sucesso", HttpStatus.CREATED);
    }
}
