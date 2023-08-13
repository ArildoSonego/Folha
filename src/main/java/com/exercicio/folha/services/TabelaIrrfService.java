package com.exercicio.folha.services;

import com.exercicio.folha.models.TabelaIrrfModel;
import com.exercicio.folha.models.TabelaIrrfPK;
import com.exercicio.folha.repositories.TabelaIrrfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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

    public boolean existeCompetencia (LocalDate dataCompetencia){
        List <TabelaIrrfModel> listaFaixaIRRF = tabelaIrrf.buscaTabelaIRRF();
        return (!dataCompetencia.isBefore(listaFaixaIRRF.get(0).getTabelaIrrfID().getCodigoTabelaIRRF()));}

}
