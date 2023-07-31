package com.exercicio.folha.services;

import com.exercicio.folha.models.EventosModel;
import com.exercicio.folha.repositories.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventosService {
    @Autowired
    EventosRepository eventos;

    public List<EventosModel> buscaTodos() {
        return eventos.findAll();
    }

    public void inclui (EventosModel eventoNovo) {
        eventos.save (eventoNovo);
    }

}