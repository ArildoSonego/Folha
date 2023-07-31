package com.exercicio.folha.repositories;


import com.exercicio.folha.models.TabelaIrrfModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TabelaIrrfRepository extends JpaRepository <TabelaIrrfModel, LocalDate> {
}
