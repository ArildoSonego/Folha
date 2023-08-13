package com.exercicio.folha.repositories;

import com.exercicio.folha.models.MovimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimentoRepository extends JpaRepository <MovimentoModel, Integer> {

    @Query(value = "SELECT count (*) FROM MOVIMENTO_TB movimento WHERE movimento.codi_emp = ?1 AND movimento.i_empregados = ?2 AND movimento.competencia = ?3 AND movimento.i_eventos = ?4", nativeQuery = true)
    int existe (int empresaID, int empregadoID, LocalDate competenciaID, int eventoID);

    @Query(value = "SELECT * FROM MOVIMENTO_TB movimento WHERE movimento.codi_emp = ?1 AND movimento.i_empregados = ?2 AND movimento.competencia = ?3 AND movimento.i_eventos = ?4", nativeQuery = true)
    MovimentoModel buscaUm (int empresaID, int empregadoID, LocalDate competenciaID, int eventoID);

    @Query(value = "SELECT * FROM MOVIMENTO_TB movimento WHERE movimento.codi_emp = ?1 AND movimento.i_empregados = ?2 AND movimento.competencia = ?3 ORDER BY movimento.codi_emp, movimento.i_empregados, movimento.competencia, movimento.i_eventos", nativeQuery = true)
    List<MovimentoModel> buscaTodos (int empresaID, int empregadoID, LocalDate competenciaID);

}
