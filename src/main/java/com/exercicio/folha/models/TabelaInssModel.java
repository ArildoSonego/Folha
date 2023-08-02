package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table (name = "TabelaInss_TB")
public class TabelaInssModel {

    @EmbeddedId
    private TabelaInssPK tabelaInssID;

    @Column(name = "taxa")
    private Double taxaINSS;

    public TabelaInssModel() {
    }

    public TabelaInssModel(TabelaInssPK tabelaInssID, Double taxaINSS) {
        this.tabelaInssID = tabelaInssID;
        this.taxaINSS = taxaINSS;
    }

    public TabelaInssPK getTabelaInssID() {
        return tabelaInssID;
    }

    public void setTabelaInssID(TabelaInssPK tabelaInssID) {
        this.tabelaInssID = tabelaInssID;
    }

    public Double getTaxaINSS() {
        return taxaINSS;
    }

    public void setTaxaINSS(Double taxaINSS) {
        this.taxaINSS = taxaINSS;
    }
}