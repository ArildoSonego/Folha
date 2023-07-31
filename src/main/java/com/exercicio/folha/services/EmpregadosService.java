package com.exercicio.folha.services;

import com.exercicio.folha.models.EmpregadosModel;
import com.exercicio.folha.repositories.EmpregadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpregadosService {
    @Autowired
    EmpregadosRepository empregados;

    public List<EmpregadosModel> buscaTodos() {
        return empregados.findAll();
    }

    public void inclui (EmpregadosModel empregadoNovo) {
        empregados.save (empregadoNovo);
    }

}
