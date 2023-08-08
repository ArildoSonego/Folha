package com.exercicio.folha.services;

import com.exercicio.folha.dtos.CalculoFolhaDto;
import com.exercicio.folha.models.*;
import com.exercicio.folha.repositories.CalculoFolhaRepository;
import com.exercicio.folha.repositories.EventosRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CalculoFolhaService {
    //@Autowired
    //CalculoFolhaRepository calculo;
    @Autowired
    EmpresasService servicoEmpresas;

    @Autowired
    EmpregadosService servicoEmpregados;

    @Autowired
    EventosRepository evento;

    public void calculoIndividual(List<LancamentosModel> listaLancamento, String resposta, HttpStatus statusHTTP, CalculoFolhaDto registroID) {
        // apresenta os dados da folha calculada na tela do servidor
        EmpresasModel empresaID = new EmpresasModel();
        empresaID = servicoEmpresas.buscaUm(registroID.codEmpresa());
        CalculoFolhaService apoioCalculo = new CalculoFolhaService();
        apoioCalculo.replicar('-',105);
        System.out.print("  Empresa   : " + empresaID.getNomeEmpresa());
        System.out.print(apoioCalculo.espacosEmBranco(empresaID.getNomeEmpresa(),60));
        System.out.println("  Competencia : " + apoioCalculo.buscaMes(registroID.competenciaID()) + "/" +  registroID.competenciaID().getYear());
        EmpregadosPK empregadoPK = new EmpregadosPK();
        empregadoPK.setCodigoEmpresa(empresaID);
        empregadoPK.setCodigoEmpregado(registroID.empregadoID());
        EmpregadosModel empregadoID = servicoEmpregados.buscaUm(empregadoPK);
        System.out.print("  Empregado : " + empregadoID.getNomeEmpregado());
        System.out.print(apoioCalculo.espacosEmBranco(empregadoID.getNomeEmpregado(),60));
        System.out.println("  Admissao    : " +  empregadoID.getDataAdmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("  Codigo  Descrição                                                       Proventos      Descontos");

        // dados dos lancamentos
        double totalLiquido = 0.0;
        double baseINSS = 0.0;
        double baseFGTS = 0.0;
        double baseIRRF = 0.0;
        double baseHoraExtra = 0.0;
        for (LancamentosModel l : listaLancamento) {
            System.out.printf("%8d",l.getCodigoEvento());
            EventosModel eventoCadastrado = evento.buscaUm(registroID.codEmpresa(), l.getCodigoEvento());
            System.out.printf("  " + eventoCadastrado.getDescricaoEvento());
            if (eventoCadastrado.getTipoEvento() == 'P')
                System.out.print(apoioCalculo.espacosEmBranco(eventoCadastrado.getDescricaoEvento(),66));
            else if (eventoCadastrado.getTipoEvento() == 'D')
                System.out.print(apoioCalculo.espacosEmBranco(eventoCadastrado.getDescricaoEvento(),81));

            // valor do salario hora
            double salarioHora = (empregadoID.getSalarioEmpregado() / 220);
            // calculo do valor a ser considerado
            double valorConsiderado = 0.0;
            if (l.getCodigoEvento() <= 100){
                if (eventoCadastrado.getUnidadeEvento() == '1'){        // Horas
                    valorConsiderado = salarioHora * l.getValorInformado();
                }
                else if (eventoCadastrado.getUnidadeEvento() == '2'){   // Valor
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
            }
            else if (l.getCodigoEvento() == 150) {  // Hora Extra
                double totalHorasExtras = (baseHoraExtra / 220) * l.getValorInformado() *  (eventoCadastrado.getTaxaBase() / 100);
                valorConsiderado = totalHorasExtras;
            }
            else if (l.getCodigoEvento() == 996) { // INSS

            }
            else if (l.getCodigoEvento() == 998) { // FGTS


            }
            else if (l.getCodigoEvento() == 999) { // IRRF

            }


            System.out.printf("%,8.2f\n",valorConsiderado);
        }
        apoioCalculo.replicar('-',105);
        resposta = empregadoID.getNomeEmpregado() + " - " + resposta;
    }

    private String buscaMes (@NotNull LocalDate dataID){
        String[] nomeMes = {"Janeiro", "Feveiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return (nomeMes[dataID.getMonthValue() -1]);
    }
    private @NotNull String espacosEmBranco(@NotNull String veio, Integer tamanhoTotal){
        Integer tamanhoString  = veio.trim().length();
        return " ".repeat(Math.max(0, (tamanhoTotal - tamanhoString) - 1));

    }
    private void replicar (Character simbolo, Integer vezes) {
        for (int cont = 1; cont <= vezes; cont++ )
            System.out.print (simbolo);
        System.out.println();

    }
}
