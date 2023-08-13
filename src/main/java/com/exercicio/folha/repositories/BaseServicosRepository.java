package com.exercicio.folha.repositories;

import com.exercicio.folha.models.BaseServicosModel;
import com.exercicio.folha.models.BaseServicosPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BaseServicosRepository extends JpaRepository <BaseServicosModel, BaseServicosPK> {

    @Query(value = "SELECT count (*) FROM BASE_SERVICOS_TB servico WHERE servico.codi_emp = ?1 AND servico.i_empregados = ?2 AND servico.competencia = ?3", nativeQuery = true)
    int existe (int empresaID, int empregadoID, LocalDate competenciaID);

    @Query(value = "SELECT * FROM BASE_SERVICOS_TB servico WHERE servico.codi_emp = ?1 AND servico.i_empregados = ?2 AND servico.competencia = ?3", nativeQuery = true)
    BaseServicosModel buscaUm (int empresaID, int empregadoID, LocalDate competenciaID);
}
