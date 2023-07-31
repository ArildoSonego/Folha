package com.exercicio.folha.services;

import com.exercicio.folha.models.MovimentoModel;
import com.exercicio.folha.repositories.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentoService {
    @Autowired
    MovimentoRepository movimento;

    public List<MovimentoModel> buscaTodos() {
        return movimento.findAll();
    }

    public void inclui (MovimentoModel movimentoNovo) {
        movimento.save (movimentoNovo);
    }

}
