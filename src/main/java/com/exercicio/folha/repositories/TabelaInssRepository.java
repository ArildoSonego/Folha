package com.exercicio.folha.repositories;

import com.exercicio.folha.models.TabelaInssModel;
import com.exercicio.folha.models.TabelaInssPK;
import com.exercicio.folha.models.TabelaIrrfModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TabelaInssRepository extends JpaRepository <TabelaInssModel, TabelaInssPK> {

    @Query(value = "SELECT COUNT(*) FROM TABELA_INSS_TB faixa WHERE faixa.i_tabcalcinss = ?1", nativeQuery = true)
    Integer existeCompetencia (LocalDate codCompetencia);

    @Query(value = "SELECT * FROM TABELA_INSS_TB faixa WHERE faixa.i_tabcalcinss = ?1 ORDER BY faixa.i_tabcalcinss, faixa.valor_ate", nativeQuery = true)
    List<TabelaInssModel> trazTabelaINSS(LocalDate codCompetencia);

}
