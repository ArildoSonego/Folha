package com.exercicio.folha.repositories;

import com.exercicio.folha.models.EventosModel;
import com.exercicio.folha.models.EventosPK;
import com.exercicio.folha.models.LancamentosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository <EventosModel, EventosPK> {

    @Query(value = "SELECT * FROM EVENTOS_TB evento WHERE evento.codi_emp = ?1 AND evento.i_eventos = ?2", nativeQuery = true)
    EventosModel buscaUm (Integer codEmpresa, Integer codEvento);

}
