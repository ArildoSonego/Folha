package com.exercicio.folha.repositories;

import com.exercicio.folha.dtos.CalculoFolhaDto;
import com.exercicio.folha.models.LancamentosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CalculoFolhaRepository extends JpaRepository <LancamentosModel, Integer> {

@Query (value = "SELECT * FROM LANCAMENTOS_TB lancamento WHERE lancamento.codi_emp = ?1 AND lancamento.i_empregados = ?2 AND lancamento.competencia = ?3 ORDER BY lancamento.codi_emp, lancamento.i_empregados,lancamento.competencia,lancamento.i_eventos ", nativeQuery = true)
List<LancamentosModel> buscaLancamentos (Integer codEmpresa, Integer codFuncionario, LocalDate codCompetencia);

}
