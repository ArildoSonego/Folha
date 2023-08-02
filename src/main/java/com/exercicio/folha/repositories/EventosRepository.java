package com.exercicio.folha.repositories;

import com.exercicio.folha.models.EventosModel;
import com.exercicio.folha.models.EventosPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository <EventosModel, EventosPK> {
}
