package com.exercicio.folha.services;

import com.exercicio.folha.models.BaseServicosModel;
import com.exercicio.folha.models.EmpregadosModel;
import com.exercicio.folha.repositories.BaseServicosRepository;
import com.exercicio.folha.repositories.EmpregadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServicosService {
    @Autowired
    BaseServicosRepository baseservicos;

    public List<BaseServicosModel> buscaTodos() {
        return baseservicos.findAll();
    }

    public void inclui (BaseServicosModel baseservicosNova) {
         baseservicos.save (baseservicosNova);
    }

    public void altera (BaseServicosModel baseservicosExistente) {
        baseservicos.save (baseservicosExistente);
    }
}
