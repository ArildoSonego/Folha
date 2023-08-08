package com.exercicio.folha.models;

import jakarta.persistence.*;


@Entity
@Table (name = "Eventos_TB")

public class EventosModel {

    @EmbeddedId
    private EventosPK eventoID;

    @Column(name = "nome", nullable = false, length = 50)
    private String descricaoEvento;

    @Column(name = "prov_desc", nullable = false)
    private Character tipoEvento;

    @Column(name = "unidade", nullable = false)
    private Character unidadeEvento;

    @Column(name = "compoe_liquido", nullable = false)
    private Character compoeLiquido;

    @Column(name = "soma_base_inss", nullable = false)
    private Character somaBaseINSS ;

    @Column(name = "soma_base_fgts", nullable = false)
    private Character somaBaseFGTS;

    @Column(name = "soma_base_irrf", nullable = false)
    private Character somaBaseIRRF;

    @Column(name = "soma_base_hextra", nullable = false)
    private Character somaBaseHoraExtra;

    @Column (name = "base_calculo")
    private Integer baseCalculo;

    @Column (name = "taxa")
    private Double taxaBase;

    public EventosModel() {
    }

    public EventosModel(EventosPK eventoID, String descricaoEvento, Character tipoEvento, Character unidadeEvento, Character compoeLiquido, Character somaBaseINSS, Character somaBaseFGTS, Character somaBaseIRRF, Character somaBaseHoraExtra, Integer baseCalculo, Double taxaBase) {
        this.eventoID = eventoID;
        this.descricaoEvento = descricaoEvento;
        this.tipoEvento = tipoEvento;
        this.unidadeEvento = unidadeEvento;
        this.compoeLiquido = compoeLiquido;
        this.somaBaseINSS = somaBaseINSS;
        this.somaBaseFGTS = somaBaseFGTS;
        this.somaBaseIRRF = somaBaseIRRF;
        this.somaBaseHoraExtra = somaBaseHoraExtra;
        this.baseCalculo = baseCalculo;
        this.taxaBase = taxaBase;
    }

    public EventosPK getEventoID() {
        return eventoID;
    }

    public void setEventoID(EventosPK eventoID) {
        this.eventoID = eventoID;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public Character getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(Character tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Character getUnidadeEvento() {
        return unidadeEvento;
    }

    public void setUnidadeEvento(Character unidadeEvento) {
        this.unidadeEvento = unidadeEvento;
    }

    public Character getCompoeLiquido() {
        return compoeLiquido;
    }

    public void setCompoeLiquido(Character compoeLiquido) {
        this.compoeLiquido = compoeLiquido;
    }

    public Character getSomaBaseINSS() {
        return somaBaseINSS;
    }

    public void setSomaBaseINSS(Character somaBaseINSS) {
        this.somaBaseINSS = somaBaseINSS;
    }

    public Character getSomaBaseFGTS() {
        return somaBaseFGTS;
    }

    public void setSomaBaseFGTS(Character somaBaseFGTS) {
        this.somaBaseFGTS = somaBaseFGTS;
    }

    public Character getSomaBaseIRRF() {
        return somaBaseIRRF;
    }

    public void setSomaBaseIRRF(Character somaBaseIRRF) {
        this.somaBaseIRRF = somaBaseIRRF;
    }

    public Character getSomaBaseHoraExtra() {
        return somaBaseHoraExtra;
    }

    public void setSomaBaseHoraExtra(Character somaBaseHoraExtra) {
        this.somaBaseHoraExtra = somaBaseHoraExtra;
    }

    public Integer gtBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(Integer baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public Double getTaxaBase() {
        return taxaBase;
    }

    public void setTaxaBase(Double taxaBase) {
        this.taxaBase = taxaBase;
    }
}