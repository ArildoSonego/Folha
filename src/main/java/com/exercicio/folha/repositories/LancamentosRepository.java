package com.exercicio.folha.repositories;

import com.exercicio.folha.models.AtividadePK;
import com.exercicio.folha.models.LancamentosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentosRepository extends JpaRepository <LancamentosModel, AtividadePK> {
}
