package com.exercicio.folha.controllers;

import com.exercicio.folha.models.*;
import com.exercicio.folha.services.EmpregadosService;
import com.exercicio.folha.services.EmpresasService;
import com.exercicio.folha.services.EventosService;
import com.exercicio.folha.services.LancamentosService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentosController {
    @Autowired
    private LancamentosService servicoLancamentos;
    @Autowired
    private EmpregadosService servicoEmpregados;
    @Autowired
    private EmpresasService servicoEmpresas;

    @Autowired
    private EventosService servicoEventos;

    @GetMapping
    public ResponseEntity <List<LancamentosModel>> buscaTodosLancamentos() {
        return new ResponseEntity<>(servicoLancamentos.buscaTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Object> incluiLancamento (@Validated @RequestBody @NotNull LancamentosModel lancamento) {
        // verifica a existencia da empresa
        if (!servicoEmpresas.existe(lancamento.getCodigoEmpresa()))
            return new ResponseEntity<>("Empresa não cadastrada.", HttpStatus.NOT_FOUND);

        // verifica a existencia do empregado
        EmpresasModel empresaID = servicoEmpresas.buscaUm(lancamento.getCodigoEmpresa());
        EmpregadosPK empregadoPK =  new EmpregadosPK();
        empregadoPK.setCodigoEmpresa(empresaID);
        empregadoPK.setCodigoEmpregado(lancamento.getCodigoEmpregado());
        if (!servicoEmpregados.existe(empregadoPK))
            return new ResponseEntity<>("Empregado não cadastrado nesta empresa.", HttpStatus.NOT_FOUND);

        // busca pela data do lancamento e pela data de competencia
        LocalDate dataLancamento = lancamento.getDataCompetencia();
        EmpregadosModel empregadoID = new EmpregadosModel();
        empregadoID = servicoEmpregados.buscaUm(empregadoPK);
        LocalDate admissaoEmpregado = empregadoID.getDataAdmissao();
        if (dataLancamento.isBefore(admissaoEmpregado))
            return new ResponseEntity<>("A competência do lançamento é anterior ao mês de admissão do empregado.", HttpStatus.NOT_ACCEPTABLE);

        // verifica a existencia do evento
        EventosPK eventoID = new EventosPK();
        eventoID.setCodigoEmpresa(empresaID);
        eventoID.setCodigoEvento(lancamento.getCodigoEvento());
        if (!servicoEventos.existe(eventoID))
            return new ResponseEntity<>("Evento não cadastrado nesta empresa.",HttpStatus.NOT_FOUND);

        // inclui o lancamento
        servicoLancamentos.inclui(lancamento);
        return new ResponseEntity<>("Lançamento incluído com sucesso.", HttpStatus.CREATED);
    }

    @DeleteMapping (value = "/{avisoExclusao}")
    public ResponseEntity <Object> excluiLancamento (@Validated @RequestBody @NotNull LancamentosModel lancamento, @PathVariable @NotNull Integer avisoExclusao) {
        if (servicoLancamentos.existe(lancamento)) {
            if (avisoExclusao.equals(1)) {
                servicoLancamentos.exclui(lancamento);
                return new ResponseEntity<>("Lançamento excluído com sucesso", HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("Confirma a exclusão do registro?", HttpStatus.OK);
            }
        else
            return new ResponseEntity<>("Lançamento inexistente.",HttpStatus.NOT_FOUND);
    }

}
