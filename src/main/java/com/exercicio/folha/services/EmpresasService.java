package com.exercicio.folha.services;

import com.exercicio.folha.models.EmpresasModel;
import com.exercicio.folha.repositories.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmpresasService {
    @Autowired
    EmpresasRepository empresas;

    public List<EmpresasModel> buscaTodos() {
        return empresas.findAll();
    }

    public EmpresasModel buscaUm (Integer empresaID){
        return empresas.findById(empresaID).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void inclui (EmpresasModel empresaNova) {
        empresas.save (empresaNova);
    }

    public boolean existe (Integer empresaID) {return empresas.existsById(empresaID);}
}
