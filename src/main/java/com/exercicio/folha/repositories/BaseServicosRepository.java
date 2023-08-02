package com.exercicio.folha.repositories;

import com.exercicio.folha.models.BaseServicosModel;
import com.exercicio.folha.models.BaseServicosPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseServicosRepository extends JpaRepository <BaseServicosModel, BaseServicosPK> {
}
