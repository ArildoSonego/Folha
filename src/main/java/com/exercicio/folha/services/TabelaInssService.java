package com.exercicio.folha.services;


import com.exercicio.folha.models.TabelaInssModel;
import com.exercicio.folha.repositories.TabelaInssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabelaInssService {
    @Autowired
    TabelaInssRepository tabelaInss;

    public List<TabelaInssModel> buscaTodos() {
        return tabelaInss.findAll();
    }

    public void inclui (TabelaInssModel tabelaInssNova) {
        tabelaInss.save (tabelaInssNova);
    }

}
