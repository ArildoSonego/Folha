package com.exercicio.folha.repositories;


import com.exercicio.folha.models.TabelaIrrfModel;
import com.exercicio.folha.models.TabelaIrrfPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TabelaIrrfRepository extends JpaRepository <TabelaIrrfModel, TabelaIrrfPK> {

    @Query(value = "SELECT COUNT(*) FROM TABELA_IRRF_TB faixa WHERE faixa.i_tabcalcirrf = ?1", nativeQuery = true)
    Integer existeCompetencia(LocalDate codCompetencia);

    @Query(value = "SELECT * FROM TABELA_IRRF_TB faixa WHERE faixa.i_tabcalcirrf = ?1 ORDER BY faixa.i_tabcalcirrf, faixa.valor_ate", nativeQuery = true)
    List<TabelaIrrfModel> trazTabelaIRRF(LocalDate codCompetencia);
}




