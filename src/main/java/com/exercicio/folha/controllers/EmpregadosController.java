package com.exercicio.folha.controllers;

import com.exercicio.folha.models.EmpregadosModel;
import com.exercicio.folha.models.EmpregadosPK;
import com.exercicio.folha.models.EmpresasModel;
import com.exercicio.folha.services.EmpregadosService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empregados")
public class EmpregadosController {
    @Autowired
    private EmpregadosService servicoEmpregados;

    @GetMapping (value = "/todos")
    public ResponseEntity <List<EmpregadosModel>> buscaTodosEmpregados() {
        return new ResponseEntity<>(servicoEmpregados.buscaTodos(), HttpStatus.OK);
    }

    @GetMapping (value = "/especifico")
    public ResponseEntity <Object> buscaEmpregado (@RequestBody @NotNull EmpregadosPK empregadoID) {
        if (servicoEmpregados.existe(empregadoID))
            return new ResponseEntity<>(servicoEmpregados.buscaUm(empregadoID),HttpStatus.OK);
        else
            return new ResponseEntity<>("Empregado inválido nesta empresa.",HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity <Object> incluiEmpregado (@Validated @RequestBody EmpregadosModel empregado) {
        servicoEmpregados.inclui(empregado);
        return new ResponseEntity<>("Empregado incluído com sucesso", HttpStatus.CREATED);
    }

}
