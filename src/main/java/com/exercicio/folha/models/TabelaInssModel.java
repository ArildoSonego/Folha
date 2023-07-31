package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table (name = "TabelaInss_TB", uniqueConstraints = {@UniqueConstraint(columnNames = {"i_tabcalcinss","valor_ate"})})
public class TabelaInssModel {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Id
    @Column(name = "i_tabcalcinss", nullable = false)
    private LocalDate codigoTabelaINSS;

    @Column(name = "valor_ate")
    private Double valorLimite;

    @Column(name = "taxa")
    private Double taxaINSS;

    public TabelaInssModel() {
    }

    public TabelaInssModel(LocalDate codigoTabelaINSS, Double valorLimite, Double taxaINSS) {
        this.codigoTabelaINSS = codigoTabelaINSS;
        this.valorLimite = valorLimite;
        this.taxaINSS = taxaINSS;
    }

    public LocalDate getCodigoTabelaINSS() {
        return codigoTabelaINSS;
    }

    public void setCodigoTabelaINSS(LocalDate codigoTabelaINSS) {
        this.codigoTabelaINSS = codigoTabelaINSS;
    }

    public Double getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(Double valorLimite) {
        this.valorLimite = valorLimite;
    }

    public Double getTaxaINSS() {
        return taxaINSS;
    }

    public void setTaxaINSS(Double taxaINSS) {
        this.taxaINSS = taxaINSS;
    }
}