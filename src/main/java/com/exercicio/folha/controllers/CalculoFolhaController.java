package com.exercicio.folha.controllers;

import com.exercicio.folha.dtos.CalculoFolhaDto;
import com.exercicio.folha.models.EmpregadosPK;
import com.exercicio.folha.models.EmpresasModel;
import com.exercicio.folha.models.LancamentosModel;
import com.exercicio.folha.repositories.CalculoFolhaRepository;
import com.exercicio.folha.repositories.TabelaInssRepository;
import com.exercicio.folha.repositories.TabelaIrrfRepository;
import com.exercicio.folha.services.CalculoFolhaService;
import com.exercicio.folha.services.EmpregadosService;
import com.exercicio.folha.services.EmpresasService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/calculoFolha")
public class CalculoFolhaController {

    @Autowired
    private EmpregadosService servicoEmpregados;

    @Autowired
    private EmpresasService servicoEmpresas;
    @Autowired
    private CalculoFolhaService servicoCalculo;

    @Autowired
    CalculoFolhaRepository calculo;

    @Autowired
    TabelaInssRepository inssServico;

    @Autowired
    TabelaIrrfRepository irrfServico;

    @PostMapping ("/individual")
    @JsonFormat(pattern="yyyy-MM-dd")
    public ResponseEntity <Object> calculoIndividual(@Validated @NotNull @RequestBody CalculoFolhaDto registroID){

        // verifica se a tabela de INSS está cadastrada para a competência
        if (inssServico.existeCompetencia(registroID.competenciaID()) == 0)
            return new ResponseEntity<>("Não existe uma tabela de cálculo de INSS para a competência informada. O cálculo não será efetuado.", HttpStatus.NOT_FOUND);

        // verifica se a tabela de IRRF está cadastrada para a competência
        if (irrfServico.existeCompetencia(registroID.competenciaID()) == 0)
            return new ResponseEntity<>("Não existe uma tabela de cálculo de IRRF para a competência informada. O cálculo não será efetuado.", HttpStatus.NOT_FOUND);

        // verifica se a empresa existe
        if (!servicoEmpresas.existe(registroID.codEmpresa()))
            return new ResponseEntity<>("Empresa não cadastrada.", HttpStatus.NOT_FOUND);

        // verifica se o empregado esta cadastrado na empresa
        EmpresasModel empresaID = new EmpresasModel();
        empresaID = servicoEmpresas.buscaUm(registroID.codEmpresa());
        EmpregadosPK empregadoID = new EmpregadosPK();
        empregadoID.setCodigoEmpresa(empresaID);
        empregadoID.setCodigoEmpregado(registroID.empregadoID());
        if (!servicoEmpregados.existe(empregadoID))
            return new ResponseEntity<>("Empregado inválido nesta empresa.", HttpStatus.NOT_FOUND);

       // verifica se existem lancamentos para o empregado na competencia indicada
        List<LancamentosModel> lancamentosPeriodo = calculo.buscaLancamentos(registroID.codEmpresa(), registroID.empregadoID(), registroID.competenciaID());
        if (lancamentosPeriodo.isEmpty())
            return new ResponseEntity<>("Nenhum lançamento encontrado para o empregado na competência informada.", HttpStatus.NOT_FOUND);

        String resultado = "Cálculo executado com sucesso.";
        HttpStatus statusHTTP = HttpStatus.OK;
        servicoCalculo.calculoIndividual(lancamentosPeriodo,resultado, statusHTTP,registroID);
        return new ResponseEntity<>(resultado, statusHTTP);
    }
}

