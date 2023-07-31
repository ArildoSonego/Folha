package com.exercicio.folha.models;

import jakarta.persistence.*;


@Entity
@Table (name = "Eventos_TB",uniqueConstraints = {@UniqueConstraint(columnNames = {"codi_emp","i_eventos"})})
public class EventosModel {

    @ManyToOne
    @JoinColumn (name = "codi_emp", nullable = false)
    private EmpresasModel codigoEmpresa;

    @Id
    @Column(name = "i_eventos", nullable = false)
    private Integer codigoEvento;

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
    private Character somaBase_HoraExtra;

    @Column (name = "base_calculo")
    private Integer baseCalculo;

    @Column (name = "taxa")
    private Double taxaBase;

    public EventosModel() {
    }

    public EventosModel(EmpresasModel codigoEmpresa, Integer codigoEvento, String descricaoEvento, Character tipoEvento, Character unidadeEvento, Character compoeLiquido, Character somaBaseINSS, Character somaBaseFGTS, Character somaBaseIRRF, Character somaBase_HoraExtra, Integer baseCalculo, Double taxaBase) {
        this.codigoEmpresa = codigoEmpresa;
        this.codigoEvento = codigoEvento;
        this.descricaoEvento = descricaoEvento;
        this.tipoEvento = tipoEvento;
        this.unidadeEvento = unidadeEvento;
        this.compoeLiquido = compoeLiquido;
        this.somaBaseINSS = somaBaseINSS;
        this.somaBaseFGTS = somaBaseFGTS;
        this.somaBaseIRRF = somaBaseIRRF;
        this.somaBase_HoraExtra = somaBase_HoraExtra;
        this.baseCalculo = baseCalculo;
        this.taxaBase = taxaBase;
    }

    public EmpresasModel getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(EmpresasModel codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public Integer getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(Integer codigoEvento) {
        this.codigoEvento = codigoEvento;
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

    public Character getSomaBase_HoraExtra() {
        return somaBase_HoraExtra;
    }

    public void setSomaBase_HoraExtra(Character somaBase_HoraExtra) {
        this.somaBase_HoraExtra = somaBase_HoraExtra;
    }

    public Integer getBaseCalculo() {
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