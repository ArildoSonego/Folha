package com.exercicio.folha.repositories;

import com.exercicio.folha.models.TabelaInssModel;
import com.exercicio.folha.models.TabelaInssPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TabelaInssRepository extends JpaRepository <TabelaInssModel, TabelaInssPK> {

    @Query(value = "SELECT * FROM TABELA_INSS_TB faixa ORDER BY faixa.i_tabcalcinss,faixa.valor_ate", nativeQuery = true)
    List <TabelaInssModel> buscaTabelaINSS ();

}
