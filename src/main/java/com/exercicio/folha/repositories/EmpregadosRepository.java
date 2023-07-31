package com.exercicio.folha.repositories;

import com.exercicio.folha.models.EmpregadosModel;
import com.exercicio.folha.models.EmpresasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpregadosRepository extends JpaRepository <EmpregadosModel,Integer> {
}
