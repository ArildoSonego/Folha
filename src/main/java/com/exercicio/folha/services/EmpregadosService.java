package com.exercicio.folha.services;

import com.exercicio.folha.models.EmpregadosModel;
import com.exercicio.folha.models.EmpregadosPK;
import com.exercicio.folha.models.EmpresasModel;
import com.exercicio.folha.repositories.EmpregadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class EmpregadosService {
    @Autowired
    EmpregadosRepository empregados;

    public void inclui (EmpregadosModel empregadoNovo) {
        empregados.save (empregadoNovo);
    }

    public EmpregadosModel buscaUm (EmpregadosPK empregadoID){
        return empregados.findById(empregadoID).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public boolean existe (EmpregadosPK empregadoID) {return empregados.existsById(empregadoID);}
}
