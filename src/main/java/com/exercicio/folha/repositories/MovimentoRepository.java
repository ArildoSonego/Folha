package com.exercicio.folha.repositories;

import com.exercicio.folha.models.AtividadePK;
import com.exercicio.folha.models.MovimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoRepository extends JpaRepository <MovimentoModel, AtividadePK> {
}
