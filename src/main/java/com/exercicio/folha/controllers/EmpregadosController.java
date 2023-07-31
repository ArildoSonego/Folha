package com.exercicio.folha.controllers;

import com.exercicio.folha.models.EmpregadosModel;
import com.exercicio.folha.services.EmpregadosService;
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

    @GetMapping
    public ResponseEntity <List<EmpregadosModel>> buscaTodosEmpregados() {
        return new ResponseEntity<>(servicoEmpregados.buscaTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Object> incluiEmpregado (@Validated @RequestBody EmpregadosModel empregado) {
        servicoEmpregados.inclui(empregado);
        return new ResponseEntity<>("Funcionário incluído com sucesso", HttpStatus.CREATED);
    }
}
