package com.exercicio.folha.services;

import com.exercicio.folha.models.LancamentosModel;
import com.exercicio.folha.repositories.LancamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentosService {
    @Autowired
    LancamentosRepository lancamentos;


    public List<LancamentosModel> buscaTodos() {
        return lancamentos.findAll();
    }


    public void inclui (LancamentosModel lancamentoNovo) {
        lancamentos.save (lancamentoNovo);
    }

    public void exclui (LancamentosModel lancamentoExistente){
        lancamentos.deleteById(lancamentoExistente.getSequencial());
    }

    public boolean existe (LancamentosModel lancamentoExistente){
        return lancamentos.existsById(lancamentoExistente.getSequencial());
    }
}
