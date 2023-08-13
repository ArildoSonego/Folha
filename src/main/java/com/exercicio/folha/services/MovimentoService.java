package com.exercicio.folha.services;

import com.exercicio.folha.models.MovimentoModel;
import com.exercicio.folha.repositories.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovimentoService {
    @Autowired
    MovimentoRepository movimento;

    public void inclui (MovimentoModel movimentoNovo) {
        movimento.save (movimentoNovo);
    }
    public void altera (MovimentoModel movimentoExistente) {movimento.save (movimentoExistente);
    }
}
