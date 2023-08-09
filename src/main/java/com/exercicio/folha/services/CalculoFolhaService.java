package com.exercicio.folha.services;

import com.exercicio.folha.dtos.CalculoFolhaDto;
import com.exercicio.folha.models.*;
import com.exercicio.folha.repositories.EventosRepository;
import com.exercicio.folha.repositories.TabelaInssRepository;
import com.exercicio.folha.repositories.TabelaIrrfRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CalculoFolhaService {

    @Autowired
    EmpresasService servicoEmpresas;

    @Autowired
    EmpregadosService servicoEmpregados;

    @Autowired
    EventosRepository servicoEventos;

    @Autowired
    TabelaIrrfRepository servicoIRRF;

    @Autowired
    TabelaInssRepository servicoINSS;

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
        apoioCalculo.adequaLista(servicoEventos,servicoIRRF,servicoINSS,listaLancamento,registroID, salarioHora);

        // apresenta os dados da folha calculada na tela do servidor
        apoioCalculo.replicar('-', 105);
        System.out.print("  Empresa   : " + empresaID.getNomeEmpresa());
        System.out.print(apoioCalculo.espacosEmBranco(empresaID.getNomeEmpresa(), 60));
        System.out.println("  Competencia : " + apoioCalculo.buscaMes(registroID.competenciaID()) + "/" + registroID.competenciaID().getYear());
        System.out.print("  Empregado : " + empregadoID.getNomeEmpregado());
        System.out.print(apoioCalculo.espacosEmBranco(empregadoID.getNomeEmpregado(), 60));
        System.out.println("  Admissao    : " + empregadoID.getDataAdmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("  Codigo  Descrição                                                       Proventos      Descontos");

        // dados dos lancamentos
        double totalLiquido = 0.0;
        double baseINSS = 0.0;
        double baseFGTS = 0.0;
        double baseIRRF = 0.0;
        double baseHoraExtra = 0.0;
        for (LancamentosModel l : listaLancamento) {
            System.out.printf("%8d", l.getCodigoEvento());
            EventosModel eventoCadastrado = servicoEventos.buscaUm(registroID.codEmpresa(), l.getCodigoEvento());
            System.out.printf("  " + eventoCadastrado.getDescricaoEvento());
            if (eventoCadastrado.getTipoEvento() == 'P')
                System.out.print(apoioCalculo.espacosEmBranco(eventoCadastrado.getDescricaoEvento(), 66));
            else if (eventoCadastrado.getTipoEvento() == 'D')
                System.out.print(apoioCalculo.espacosEmBranco(eventoCadastrado.getDescricaoEvento(), 81));

            // valor do salario hora

            // calculo do valor a ser considerado
            double valorConsiderado = 0.0;
            if (l.getCodigoEvento() <= 100) {
                if (eventoCadastrado.getUnidadeEvento() == '1') {        // Horas
                    valorConsiderado = salarioHora * l.getValorInformado();
                } else if (eventoCadastrado.getUnidadeEvento() == '2') {   // Valor
                    valorConsiderado = l.getValorInformado();
                }
                int multiplicador = 1;
                if (eventoCadastrado.getTipoEvento() == 'D')
                    multiplicador = -1;

                if (eventoCadastrado.getCompoeLiquido() == 'S')
                    totalLiquido += valorConsiderado * multiplicador;

                if (eventoCadastrado.getSomaBaseINSS() == 'S')
                    baseINSS += valorConsiderado * multiplicador;

                if (eventoCadastrado.getSomaBaseFGTS() == 'S')
                    baseFGTS += valorConsiderado * multiplicador;

                if (eventoCadastrado.getSomaBaseHoraExtra() == 'S')
                    baseHoraExtra += valorConsiderado * multiplicador;

                if (eventoCadastrado.getSomaBaseIRRF() == 'S')
                    baseIRRF += valorConsiderado * multiplicador;
            } else if (l.getCodigoEvento() == 150) {  // Hora Extra
                double totalHorasExtras = (baseHoraExtra / 220) * l.getValorInformado() * (eventoCadastrado.getTaxaBase() / 100);
                valorConsiderado = totalHorasExtras;
            } else if (l.getCodigoEvento() == 996) { // FGTS

            } else if (l.getCodigoEvento() == 998) { // INSS


            } else if (l.getCodigoEvento() == 999) { // IRRF

            }

            System.out.printf("%,8.2f\n", valorConsiderado);
        }
        apoioCalculo.replicar('-', 105);

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

    private void adequaLista(EventosRepository servicoEventos, TabelaIrrfRepository servicoIRRF, TabelaInssRepository servicoINSS, List<LancamentosModel> listaLancamentos,CalculoFolhaDto registroID, double salarioHora) {
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

        // verifica a existencia do evento 999 - IRRF
        if (!apoioCalculo.foiLancado(listaLancamentos,999)) {
            LancamentosModel novoLancamento = new LancamentosModel();
            novoLancamento.setSequencial(0);
            novoLancamento.setCodigoEmpresa(registroID.codEmpresa());
            novoLancamento.setCodigoEmpregado(registroID.empregadoID());
            novoLancamento.setDataCompetencia(registroID.competenciaID());
            novoLancamento.setCodigoEvento(999);
            novoLancamento.setValorInformado(apoioCalculo.calculaIRRF(servicoEventos, servicoIRRF, listaLancamentos, salarioHora, registroID));
            listaLancamentos.add(listaLancamentos.size(), novoLancamento);
        }

        // verifica a existencia do evento 998 - INSS
        if (!apoioCalculo.foiLancado(listaLancamentos,998)){
            LancamentosModel novoLancamento = new LancamentosModel();
            novoLancamento.setSequencial(0);
            novoLancamento.setCodigoEmpresa(registroID.codEmpresa());
            novoLancamento.setCodigoEmpregado(registroID.empregadoID());
            novoLancamento.setDataCompetencia(registroID.competenciaID());
            novoLancamento.setCodigoEvento(998);
            novoLancamento.setValorInformado(apoioCalculo.calculaINSS(servicoEventos,servicoINSS, listaLancamentos,salarioHora,registroID));
            listaLancamentos.add(listaLancamentos.size() -1,novoLancamento);
        }

        // verifica a existencia do evento 996 - FGTS
        if (!apoioCalculo.foiLancado(listaLancamentos,996)){
            LancamentosModel novoLancamento = new LancamentosModel();
            novoLancamento.setSequencial(0);
            novoLancamento.setCodigoEmpresa(registroID.codEmpresa());
            novoLancamento.setCodigoEmpregado(registroID.empregadoID());
            novoLancamento.setDataCompetencia(registroID.competenciaID());
            novoLancamento.setCodigoEvento(996);
            novoLancamento.setValorInformado(apoioCalculo.calculaFGTS(servicoEventos,listaLancamentos,salarioHora,registroID));
            listaLancamentos.add(listaLancamentos.size() -2,novoLancamento);
        }
    }

    private boolean foiLancado(@NotNull List<LancamentosModel> listaLancamentos, Integer codEvento) {
        return listaLancamentos.stream().anyMatch(evento -> evento.getCodigoEvento().equals(codEvento));
    }

    private double calculaIRRF (EventosRepository servicoEventos,TabelaIrrfRepository servicoIRRF, List <LancamentosModel> listaLancamento, double salarioHora, CalculoFolhaDto registroID) {

        double irrfBase = 0.0;
        double irrfTaxa = 0.0;
        double irrfDesconto = 0.0;

        for (LancamentosModel l : listaLancamento) {

            EventosModel eventoCadastrado = servicoEventos.buscaUm(registroID.codEmpresa(), l.getCodigoEvento());

            // calculo do valor a ser considerado
            double valorConsiderado = 0.0;
            if ((l.getCodigoEvento() <= 100) && (eventoCadastrado.getSomaBaseIRRF() == 'S')) {
                if (eventoCadastrado.getUnidadeEvento() == '1') {          // Horas
                    valorConsiderado = salarioHora * l.getValorInformado();
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
            List <TabelaIrrfModel> faixaIRRF = servicoIRRF.trazTabelaIRRF(registroID.competenciaID());
            for (TabelaIrrfModel t : faixaIRRF) {
                TabelaIrrfPK limiteValor = t.getTabelaIrrfID();
                if (irrfBase <= limiteValor.getValorLimite()) {
                    irrfTaxa = t.getTaxaIRRF();
                    irrfDesconto = t.getDescontoIRRF();
                }
            }
    return (irrfBase * irrfTaxa / 100 - irrfDesconto);
    }

    private double calculaINSS (EventosRepository servicoEventos,TabelaInssRepository servicoINSS, List <LancamentosModel> listaLancamento, double salarioHora, CalculoFolhaDto registroID) {

        double inssBase = 0.0;
        double inssTaxa = 0.0;

        for (LancamentosModel l : listaLancamento) {
            EventosModel eventoCadastrado = servicoEventos.buscaUm(registroID.codEmpresa(), l.getCodigoEvento());

            // calculo do valor a ser considerado
            double valorConsiderado = 0.0;
            if ((l.getCodigoEvento() <= 100) && (eventoCadastrado.getSomaBaseINSS() == 'S')) {
                if (eventoCadastrado.getUnidadeEvento() == '1') {          // Horas
                    valorConsiderado = salarioHora * l.getValorInformado();
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
        List <TabelaInssModel> faixaINSS = servicoINSS.trazTabelaINSS(registroID.competenciaID());
        for (TabelaInssModel t : faixaINSS) {
            TabelaInssPK limiteValor = t.getTabelaInssID();
            if (inssBase <= limiteValor.getValorLimite())
                inssTaxa = t.getTaxaINSS();
        }
        return (inssBase * inssTaxa / 100);
    }

    private double calculaFGTS (EventosRepository servicoEventos,List <LancamentosModel> listaLancamento, double salarioHora, CalculoFolhaDto registroID) {

        double fgtsBase = 0.0;

        for (LancamentosModel l : listaLancamento) {
            EventosModel eventoCadastrado = servicoEventos.buscaUm(registroID.codEmpresa(), l.getCodigoEvento());

            // calculo do valor a ser considerado
            double valorConsiderado = 0.0;
            if ((l.getCodigoEvento() <= 100) && (eventoCadastrado.getSomaBaseFGTS() == 'S')) {
                if (eventoCadastrado.getUnidadeEvento() == '1') {          // Horas
                    valorConsiderado = salarioHora * l.getValorInformado();
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
        return (fgtsBase * eventoCadastrado.getTaxaBase() / 100);
    }
}