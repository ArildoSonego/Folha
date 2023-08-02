package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table (name = "TabelaIrrf_TB", uniqueConstraints = {@UniqueConstraint(columnNames = {"i_tabcalcirrf","valor_ate"})})
public class TabelaIrrfModel {

    @EmbeddedId
    private TabelaIrrfPK tabelaIrrfID;

    @Column (name = "taxa")
    private Double taxaIRRF;

    @Column (name = "desconto")
    private Double descontoIRRF;

    public TabelaIrrfModel() {
    }

    public TabelaIrrfModel(TabelaIrrfPK tabelaIrrfID, Double taxaIRRF, Double descontoIRRF) {
        this.tabelaIrrfID = tabelaIrrfID;
        this.taxaIRRF = taxaIRRF;
        this.descontoIRRF = descontoIRRF;
    }

    public TabelaIrrfPK getTabelaIrrfID() {
        return tabelaIrrfID;
    }

    public void setTabelaIrrfID(TabelaIrrfPK tabelaIrrfID) {
        this.tabelaIrrfID = tabelaIrrfID;
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