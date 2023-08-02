package com.exercicio.folha.repositories;


import com.exercicio.folha.models.TabelaInssModel;
import com.exercicio.folha.models.TabelaInssPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TabelaInssRepository extends JpaRepository <TabelaInssModel, TabelaInssPK> {
}
