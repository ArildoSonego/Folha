package com.exercicio.folha.services;


import com.exercicio.folha.models.TabelaInssModel;
import com.exercicio.folha.models.TabelaInssPK;
import com.exercicio.folha.repositories.TabelaInssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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

    public boolean existeCompetencia (LocalDate dataCompetencia){
        List <TabelaInssModel> listaFaixaINSS = tabelaInss.buscaTabelaINSS();
        TabelaInssPK tabINSS;
        for (TabelaInssModel faixa: listaFaixaINSS) {
            tabINSS = faixa.getTabelaInssID();
            if (tabINSS.getCodigoTabelaINSS().isAfter(dataCompetencia))
                return false;
        }
        return true;
    }

}
