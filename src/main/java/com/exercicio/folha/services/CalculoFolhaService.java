package com.exercicio.folha.services;

import com.exercicio.folha.dtos.CalculoFolhaDto;
import com.exercicio.folha.dtos.IndicesGlobaisDto;
import com.exercicio.folha.models.*;
import com.exercicio.folha.repositories.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CalculoFolhaService {

    @Autowired
    private EmpresasService servicoEmpresas;

    @Autowired
    private EmpregadosService servicoEmpregados;

    @Autowired
    private EventosRepository servicoEventos;

    @Autowired
    private TabelaIrrfRepository servicoIRRF;

    @Autowired
    private TabelaInssRepository servicoINSS;

    @Autowired
    private MovimentoService servicoMovimento;

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private BaseServicosRepository baseRepository;

    @Autowired
    private BaseServicosService baseServicos;

    public void calculoIndividual(@NotNull List<LancamentosModel> listaLancamento, @NotNull CalculoFolhaDto registroID) {
        // prepara a lista de lancamentos
        EmpresasModel empresaID = new EmpresasModel();
        empresaID = servicoEmpresas.buscaUm(registroID.codEmpresa());
        EmpregadosPK empregadoPK = new EmpregadosPK();
        empregadoPK.setCodigoEmpresa(empresaID);
        empregadoPK.setCodigoEmpregado(registroID.empregadoID());
        EmpregadosModel empregadoID = servicoEmpregados.buscaUm(empregadoPK);
        double salarioHora = (empregadoID.getSalarioEmpregado() / 220);
        CalculoFolhaService apoioCalculo = new CalculoFolhaService();
        IndicesGlobaisDto indice = new IndicesGlobaisDto(0.0,0.0,0.0,0.0);
        apoioCalculo.adequaLista(servicoEventos,servicoIRRF,servicoINSS,listaLancamento,registroID,salarioHora,indice);

        double totalINSS = 0.0;
        double totalFGTS = 0.0;
        double totalIRRF = 0.0;
        double totalHoraExtra = 0;
        double totalProventos = 0.0;
        double totalDescontos = 0.0;
        // processa os dados dos lancamentos
        for (LancamentosModel l : listaLancamento) {
            EventosModel eventoCadastrado = servicoEventos.buscaUm(registroID.codEmpresa(), l.getCodigoEvento());

            // calculo do valor a ser considerado
            double valorConsiderado = 0.0;
            if (eventoCadastrado.getUnidadeEvento() == '1') {          // Horas
                valorConsiderado = salarioHora * l.getValorInformado() * eventoCadastrado.getTaxaBase() / 100;
            } else if (eventoCadastrado.getUnidadeEvento() == '2') {   // Valor
                valorConsiderado = l.getValorInformado();
            }

            // coleta dos dados para a tabela de base de servicos
            if (eventoCadastrado.getCompoeLiquido() == 'S')
                if (eventoCadastrado.getTipoEvento() == 'P')
                    totalProventos += valorConsiderado;
                else if (eventoCadastrado.getTipoEvento() == 'D')
                    totalDescontos += valorConsiderado;

            if (eventoCadastrado.getSomaBaseINSS() == 'S')
                totalINSS += valorConsiderado;

            if (eventoCadastrado.getSomaBaseFGTS() == 'S')
                totalFGTS += valorConsiderado;

            if (eventoCadastrado.getSomaBaseHoraExtra() == 'S')
                totalHoraExtra += valorConsiderado;

            if (eventoCadastrado.getSomaBaseIRRF() == 'S')
                if (eventoCadastrado.getTipoEvento() == 'P')
                    totalIRRF += valorConsiderado;
                else if (eventoCadastrado.getTipoEvento() == 'D')
                    totalIRRF -= valorConsiderado;

            // grava os dados na tabela de movimentacao
            if (valorConsiderado > 0) {
                MovimentoModel movimentacao = new MovimentoModel();
                movimentacao.setCodigoEmpresa(l.getCodigoEmpresa());
                movimentacao.setCodigoEmpregado(l.getCodigoEmpregado());
                movimentacao.setDataCompetencia(l.getDataCompetencia());
                movimentacao.setCodigoEvento(l.getCodigoEvento());
                movimentacao.setTipoMovimento(eventoCadastrado.getTipoEvento());
                if (l.getCodigoEvento() <= 995)
                    movimentacao.setValorInformado(l.getValorInformado());
                else if (l.getCodigoEvento() == 996) // FGTS
                    movimentacao.setValorInformado(indice.getIndiceFGTS());
                else if (l.getCodigoEvento() == 998) // INSS
                    movimentacao.setValorInformado(indice.getIndiceINSS());
                else if (l.getCodigoEvento() == 999) // FGTS
                    movimentacao.setValorInformado(indice.getIndiceIRRF());
                movimentacao.setValorCalculado(valorConsiderado);
                if (movimentoRepository.existe(l.getCodigoEmpresa(), l.getCodigoEmpregado(), l.getDataCompetencia(), l.getCodigoEvento()) > 0) {
                    int seqMovimento = movimentoRepository.buscaUm(l.getCodigoEmpresa(), l.getCodigoEmpregado(), l.getDataCompetencia(), l.getCodigoEvento()).getSequencial();
                    movimentacao.setSequencial(seqMovimento);
                    servicoMovimento.altera(movimentacao);
                }
                else
                    servicoMovimento.inclui(movimentacao);
            }
        }
        // grava os dados na tabela de base de servicos
        BaseServicosModel servicosBase = new BaseServicosModel();
        BaseServicosPK servicosPK  = new BaseServicosPK();
        servicosPK.setCodigoEmpregado(empregadoID);
        servicosPK.setDataCompetencia(registroID.competenciaID());
        servicosBase.setBaseServicoId(servicosPK);
        servicosBase.setBaseINSS(totalINSS - indice.getExcedenteINSS());
        servicosBase.setBaseExcedenteINSS(indice.getExcedenteINSS());
        servicosBase.setBaseFGTS(totalFGTS);
        servicosBase.setBaseIRRF(totalIRRF);
        servicosBase.setBaseHoraExtra(totalHoraExtra);
        servicosBase.setValorProventos(totalProventos);
        servicosBase.setValorDescontos(totalDescontos);
        if (baseRepository.existe(registroID.codEmpresa(), registroID.empregadoID(), registroID.competenciaID()) > 0)
            baseServicos.altera(servicosBase);
        else
            baseServicos.inclui(servicosBase);
    }

    private String buscaMes(@NotNull LocalDate dataID) {
        String[] nomeMes = {"Janeiro", "Feveiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return (nomeMes[dataID.getMonthValue() - 1]);
    }

    private @NotNull String espacosEmBranco(@NotNull String veio, Integer tamanhoTotal) {
        Integer tamanhoString = veio.trim().length();
        return " ".repeat(Math.max(0, (tamanhoTotal - tamanhoString) - 1));

    }

    private void replicar(Character simbolo, Integer vezes) {
        for (int cont = 1; cont <= vezes; cont++)
            System.out.print(simbolo);
        System.out.println();

    }

    private void adequaLista(EventosRepository servicoEventos, TabelaIrrfRepository servicoIRRF, TabelaInssRepository servicoINSS, List<LancamentosModel> listaLancamentos,CalculoFolhaDto registroID, double salarioHora, IndicesGlobaisDto indice) {
        CalculoFolhaService apoioCalculo = new CalculoFolhaService();

        // verifica a existencia do evento 1 = Horas Trabalhadas
        if (!apoioCalculo.foiLancado(listaLancamentos, 1)){
            LancamentosModel novoLancamento = new LancamentosModel();
            novoLancamento.setSequencial(0);
            novoLancamento.setCodigoEmpresa(registroID.codEmpresa());
            novoLancamento.setCodigoEmpregado(registroID.empregadoID());
            novoLancamento.setDataCompetencia(registroID.competenciaID());
            novoLancamento.setCodigoEvento(1);
            novoLancamento.setValorInformado(0.0);
            listaLancamentos.add(0,novoLancamento);
        }

        // verifica a existencia do evento 996 - FGTS
        if (!apoioCalculo.foiLancado(listaLancamentos,996)){
            LancamentosModel novoLancamento = new LancamentosModel();
            novoLancamento.setSequencial(0);
            novoLancamento.setCodigoEmpresa(registroID.codEmpresa());
            novoLancamento.setCodigoEmpregado(registroID.empregadoID());
            novoLancamento.setDataCompetencia(registroID.competenciaID());
            novoLancamento.setCodigoEvento(996);
            novoLancamento.setValorInformado(apoioCalculo.calculaFGTS(servicoEventos,listaLancamentos,salarioHora,registroID,indice));
            listaLancamentos.add(listaLancamentos.size(),novoLancamento);
        }

        // verifica a existencia do evento 998 - INSS
        if (!apoioCalculo.foiLancado(listaLancamentos,998)){
            LancamentosModel novoLancamento = new LancamentosModel();
            novoLancamento.setSequencial(0);
            novoLancamento.setCodigoEmpresa(registroID.codEmpresa());
            novoLancamento.setCodigoEmpregado(registroID.empregadoID());
            novoLancamento.setDataCompetencia(registroID.competenciaID());
            novoLancamento.setCodigoEvento(998);
            novoLancamento.setValorInformado(apoioCalculo.calculaINSS(servicoEventos,servicoINSS, listaLancamentos,salarioHora,registroID,indice));
            listaLancamentos.add(listaLancamentos.size(),novoLancamento);
        }

        // verifica a existencia do evento 999 - IRRF
        if (!apoioCalculo.foiLancado(listaLancamentos,999)) {
            LancamentosModel novoLancamento = new LancamentosModel();
            novoLancamento.setSequencial(0);
            novoLancamento.setCodigoEmpresa(registroID.codEmpresa());
            novoLancamento.setCodigoEmpregado(registroID.empregadoID());
            novoLancamento.setDataCompetencia(registroID.competenciaID());
            novoLancamento.setCodigoEvento(999);
            novoLancamento.setValorInformado(apoioCalculo.calculaIRRF(servicoEventos, servicoIRRF, listaLancamentos, salarioHora, registroID,indice));
            listaLancamentos.add(listaLancamentos.size(), novoLancamento);
        }
    }

    private boolean foiLancado(@NotNull List<LancamentosModel> listaLancamentos, Integer codEvento) {
        return listaLancamentos.stream().anyMatch(evento -> evento.getCodigoEvento().equals(codEvento));
    }

    private double calculaIRRF (EventosRepository servicoEventos,TabelaIrrfRepository servicoIRRF, List <LancamentosModel> listaLancamento, double salarioHora, CalculoFolhaDto registroID, IndicesGlobaisDto indice) {

        double irrfBase = 0.0;
        double irrfTaxa = 0.0;
        double irrfDesconto = 0.0;

        for (LancamentosModel l : listaLancamento) {

            EventosModel eventoCadastrado = servicoEventos.buscaUm(registroID.codEmpresa(), l.getCodigoEvento());

            // calculo do valor a ser considerado
            double valorConsiderado = 0.0;
            if ((eventoCadastrado.getSomaBaseIRRF() == 'S')) {
                if (eventoCadastrado.getUnidadeEvento() == '1') {          // Horas
                    valorConsiderado = salarioHora * l.getValorInformado() * eventoCadastrado.getTaxaBase() / 100;
                } else if (eventoCadastrado.getUnidadeEvento() == '2') {   // Valor
                    valorConsiderado = l.getValorInformado();
                }
                int multiplicador = 1;
                if (eventoCadastrado.getTipoEvento() == 'D')
                    multiplicador = -1;
                irrfBase += valorConsiderado * multiplicador;
            }
        }
        // busca o indice de desconto na tabela
        List <TabelaIrrfModel> faixaIRRF = servicoIRRF.buscaTabelaIRRF();
        double valorAnterior = -1.0;
        for (TabelaIrrfModel t : faixaIRRF) {
                if (!registroID.competenciaID().isBefore(t.getTabelaIrrfID().getCodigoTabelaIRRF()) && (irrfBase > valorAnterior ) && (irrfBase <= t.getTabelaIrrfID().getValorLimite())) {
                    irrfTaxa = t.getTaxaIRRF();
                    irrfDesconto = t.getDescontoIRRF();
                }
                valorAnterior = t.getTabelaIrrfID().getValorLimite();
        }
        indice.setIndiceIRRF(irrfTaxa);
        return (irrfBase * irrfTaxa / 100 - irrfDesconto);
    }

    private double calculaINSS (EventosRepository servicoEventos,TabelaInssRepository servicoINSS, List <LancamentosModel> listaLancamento, double salarioHora, CalculoFolhaDto registroID, IndicesGlobaisDto indice) {

        double inssBase = 0.0;
        double inssTaxa = 0.0;

        for (LancamentosModel l : listaLancamento) {
            EventosModel eventoCadastrado = servicoEventos.buscaUm(registroID.codEmpresa(), l.getCodigoEvento());

            // calculo do valor a ser considerado
            double valorConsiderado = 0.0;
            if ((eventoCadastrado.getSomaBaseINSS() == 'S')) {
                if (eventoCadastrado.getUnidadeEvento() == '1') {          // Horas
                    valorConsiderado = salarioHora * l.getValorInformado() * eventoCadastrado.getTaxaBase() / 100;
                } else if (eventoCadastrado.getUnidadeEvento() == '2') {   // Valor
                    valorConsiderado = l.getValorInformado();
                }
                int multiplicador = 1;
                if (eventoCadastrado.getTipoEvento() == 'D')
                    multiplicador = -1;
                inssBase += valorConsiderado * multiplicador;
            }
        }

        // busca o indice de desconto na tabela
        List <TabelaInssModel> faixaINSS = servicoINSS.buscaTabelaINSS();
        double valorAnterior = -1.0;
        double valorUsar = 0.0;
        for (TabelaInssModel t : faixaINSS) {
            if (!registroID.competenciaID().isBefore(t.getTabelaInssID().getCodigoTabelaINSS()) && (inssBase > valorAnterior ) && (inssBase <= t.getTabelaInssID().getValorLimite())) {
                inssTaxa = t.getTaxaINSS();
                if (t.getTabelaInssID().getValorLimite().equals(99999999.99))
                    valorUsar = valorAnterior;
            }
            valorAnterior = t.getTabelaInssID().getValorLimite();
        }

        if (valorUsar > 0.0){
            indice.setExcedenteINSS(inssBase - valorUsar);
            inssBase = valorUsar;
        }
        indice.setIndiceINSS(inssTaxa);
        return (inssBase * inssTaxa / 100);
    }

    private double calculaFGTS (EventosRepository servicoEventos,List <LancamentosModel> listaLancamento, double salarioHora, CalculoFolhaDto registroID, IndicesGlobaisDto indice) {

        double fgtsBase = 0.0;

        for (LancamentosModel l : listaLancamento) {
            EventosModel eventoCadastrado = servicoEventos.buscaUm(registroID.codEmpresa(), l.getCodigoEvento());

            // calculo do valor a ser considerado
            double valorConsiderado = 0.0;
            if ((eventoCadastrado.getSomaBaseFGTS() == 'S')) {
                if (eventoCadastrado.getUnidadeEvento() == '1') {          // Horas
                    valorConsiderado = salarioHora * l.getValorInformado() * eventoCadastrado.getTaxaBase() / 100;
                } else if (eventoCadastrado.getUnidadeEvento() == '2') {   // Valor
                    valorConsiderado = l.getValorInformado();
                }
                int multiplicador = 1;
                if (eventoCadastrado.getTipoEvento() == 'D')
                    multiplicador = -1;
                fgtsBase += valorConsiderado * multiplicador;
            }
        }

        // busca o indice na tabela
        EventosModel eventoCadastrado = servicoEventos.buscaUm(registroID.codEmpresa(), 996);
        indice.setIndiceFGTS(eventoCadastrado.getTaxaBase());
        return (fgtsBase * eventoCadastrado.getTaxaBase() / 100);
    }

    public void reciboIndividual(@NotNull List<MovimentoModel> listaMovimento, @NotNull CalculoFolhaDto registroID) {

        // apresenta os dados da folha calculada na tela do servidor
        EmpresasModel empresaID = new EmpresasModel();
        empresaID = servicoEmpresas.buscaUm(registroID.codEmpresa());
        EmpregadosPK empregadoPK = new EmpregadosPK();
        empregadoPK.setCodigoEmpresa(empresaID);
        empregadoPK.setCodigoEmpregado(registroID.empregadoID());
        EmpregadosModel empregadoID = servicoEmpregados.buscaUm(empregadoPK);
        CalculoFolhaService apoioCalculo = new CalculoFolhaService();

        apoioCalculo.replicar('-', 105);
        System.out.print("  Empresa   : " + empresaID.getNomeEmpresa());
        System.out.print(apoioCalculo.espacosEmBranco(empresaID.getNomeEmpresa(), 60));
        System.out.println("  Competencia : " + apoioCalculo.buscaMes(registroID.competenciaID()) + "/" + registroID.competenciaID().getYear());
        System.out.print("  Empregado : " + empregadoID.getNomeEmpregado());
        System.out.print(apoioCalculo.espacosEmBranco(empregadoID.getNomeEmpregado(), 60));
        System.out.println("  Admissao    : " + empregadoID.getDataAdmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("  Codigo  Descrição                                                       Proventos      Descontos");

        double totalProventos = 0.0;
        double totalDescontos = 0.0;
        for (MovimentoModel m : listaMovimento) {
            System.out.printf("%8d", m.getCodigoEvento());
            EventosModel eventoCadastrado = servicoEventos.buscaUm(registroID.codEmpresa(), m.getCodigoEvento());
            System.out.printf("  " + eventoCadastrado.getDescricaoEvento());
            if (eventoCadastrado.getTipoEvento() == 'P') {
                System.out.print(apoioCalculo.espacosEmBranco(eventoCadastrado.getDescricaoEvento(), 66));
                if (m.getCodigoEvento() != 996) // FGTS
                    totalProventos += m.getValorCalculado();
            }
            else if (eventoCadastrado.getTipoEvento() == 'D'){
                System.out.print(apoioCalculo.espacosEmBranco(eventoCadastrado.getDescricaoEvento(), 81));
                totalDescontos += m.getValorCalculado();
            }
            System.out.printf("%,8.2f\n", m.getValorCalculado());
        }
        apoioCalculo.replicar('-', 105);
        System.out.print ("     Proventos : ");
        System.out.printf("%,8.2f", totalProventos);
        System.out.print ("              Descontos : ");
        System.out.printf("%,8.2f", totalDescontos);
        System.out.print ("             Total a receber : ");
        System.out.printf("%,8.2f\n", totalProventos - totalDescontos);
        apoioCalculo.replicar('-', 105);
    }

}