package com.exercicio.folha.repositories;

import com.exercicio.folha.models.EmpregadosModel;
import com.exercicio.folha.models.EmpregadosPK;
import com.exercicio.folha.models.EmpresasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpregadosRepository extends JpaRepository <EmpregadosModel, EmpregadosPK> {

@Query(value = "SELECT * FROM EMPREGADOS_TB empregado WHERE empregado.codi_emp = ?1 ORDER BY empregado.codi_emp, empregado.nome", nativeQuery = true)
List<EmpregadosModel> buscaTodos(int codEmpresa);
}
