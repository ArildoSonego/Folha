package com.exercicio.folha.controllers;

import com.exercicio.folha.models.EventosModel;
import com.exercicio.folha.repositories.EventosRepository;
import com.exercicio.folha.services.EventosService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventosController {
    final Integer[] vetorEventos = {1,150,996,998,999};
    final List <Integer> eventosFixos = Arrays.asList(vetorEventos);

    @Autowired
    private EventosService servicoEventos;

    @Autowired
    private EventosRepository eventosEmpresa;

    @GetMapping (value = "/todos/{empresaID}")
    public ResponseEntity <List<EventosModel>> buscaTodosEventos(@PathVariable int empresaID) {
        return new ResponseEntity<>(eventosEmpresa.buscaTodos(empresaID), HttpStatus.OK);
    }

    @PutMapping (value="/{avisoAlteracao}")
    public ResponseEntity <Object> alteraEvento (@Validated @RequestBody @NotNull EventosModel evento, @PathVariable Integer avisoAlteracao) {

     if (eventosFixos.contains(evento.getEventoID().getCodigoEvento()) && (avisoAlteracao.equals(0))) {
            return new ResponseEntity<>("Não recomenda-se a alteração deste evento. Deseja continuar?", HttpStatus.OK);
        }
        else
            {
            servicoEventos.altera(evento);
            return new ResponseEntity<>("Evento alterado com sucesso", HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity <Object> incluiEvento (@Validated @RequestBody @NotNull EventosModel evento) {
        if (evento.getEventoID().getCodigoEvento() <= 100){
            servicoEventos.inclui(evento);
            return new ResponseEntity<>("Evento incluído com sucesso", HttpStatus.CREATED);}
        else
            return new ResponseEntity<>("Não é permitida a inclusão de eventos com código superior a 100.", HttpStatus.NOT_ACCEPTABLE);
    }
}
