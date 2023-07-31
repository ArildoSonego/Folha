package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table (name = "TabelaIrrf_TB", uniqueConstraints = {@UniqueConstraint(columnNames = {"i_tabcalcirrf","valor_ate"})})
public class TabelaIrrfModel {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Id
    @Column(name = "i_tabcalcirrf", nullable = false)
    private LocalDate codigoTabelaIRRF;

    @Column (name = "valor_ate")
    private Double valorLimite;

    @Column (name = "taxa")
    private Double taxaIRRF;

    @Column (name = "desconto")
    private Double descontoIRRF;

    public TabelaIrrfModel() {
    }

    public TabelaIrrfModel(LocalDate codigoTabelaIRRF, Double valorLimite, Double taxaIRRF, Double descontoIRRF) {
        this.codigoTabelaIRRF = codigoTabelaIRRF;
        this.valorLimite = valorLimite;
        this.taxaIRRF = taxaIRRF;
        this.descontoIRRF = descontoIRRF;
    }

    public LocalDate getCodigoTabelaIRRF() {
        return codigoTabelaIRRF;
    }

    public void setCodigoTabelaIRRF(LocalDate codigoTabelaIRRF) {
        this.codigoTabelaIRRF = codigoTabelaIRRF;
    }

    public Double getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(Double valorLimite) {
        this.valorLimite = valorLimite;
    }

    public Double getTaxaIRRF() {
        return taxaIRRF;
    }

    public void setTaxaIRRF(Double taxaIRRF) {
        this.taxaIRRF = taxaIRRF;
    }

    public Double getDescontoIRRF() {
        return descontoIRRF;
    }

    public void setDescontoIRRF(Double descontoIRRF) {
        this.descontoIRRF = descontoIRRF;
    }
}