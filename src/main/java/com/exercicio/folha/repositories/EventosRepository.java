package com.exercicio.folha.repositories;

import com.exercicio.folha.models.EventosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository <EventosModel,Integer> {
}
