package com.exercicio.folha.services;

import com.exercicio.folha.models.TabelaIrrfModel;
import com.exercicio.folha.repositories.TabelaIrrfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabelaIrrfService {
    @Autowired
    TabelaIrrfRepository tabelaIrrf;

    public List<TabelaIrrfModel> buscaTodos() {
        return tabelaIrrf.findAll();
    }

    public void inclui (TabelaIrrfModel tabelaIrrfNova) {
        tabelaIrrf.save (tabelaIrrfNova);
    }

}
