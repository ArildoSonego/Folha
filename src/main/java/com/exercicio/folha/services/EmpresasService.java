package com.exercicio.folha.services;

import com.exercicio.folha.models.EmpresasModel;
import com.exercicio.folha.repositories.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresasService {
    @Autowired
    EmpresasRepository empresas;

    public List<EmpresasModel> buscaTodos() {
        return empresas.findAll();
    }

    public void inclui (EmpresasModel empresaNova) {
        empresas.save (empresaNova);
    }

}
