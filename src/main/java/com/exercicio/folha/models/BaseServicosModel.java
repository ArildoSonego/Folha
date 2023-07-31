package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table (name = "BaseServicos_TB", uniqueConstraints = {@UniqueConstraint(columnNames = {"codi_emp","i_empregados", "competencia"})})
public class BaseServicosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "codi_seq", nullable = false)
    private Integer codigoSequencial;

    @ManyToOne
    @JoinColumn (name = "codi_emp", nullable = false)
    private EmpresasModel codigoEmpresa;

    @ManyToOne
    @JoinColumn(name = "i_empregados", nullable = false)
    private EmpregadosModel codigoEmpregado;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "competencia", nullable = false)
    private LocalDate dataCompetencia;

    @Column (name = "base_inss")
    private Double baseINSS;

    @Column (name = "base_excedente_inss")
    private Double baseExcedenteINSS;

    @Column (name = "base_fgts")
    private Double baseFGTS;

    @Column (name = "base_irrf")
    private Double baseIRRF;

    @Column (name = "base_hextra")
    private Double baseHoraExtra;

    @Column (name = "proventos")
    private Double valorProventos;

    @Column (name = "descontos")
    private Double valorDescontos;

    public BaseServicosModel() {
    }

    public BaseServicosModel(Integer codigoSequencial, EmpresasModel codigoEmpresa, EmpregadosModel codigoEmpregado, LocalDate dataCompetencia, Double baseINSS, Double baseExcedenteINSS, Double baseFGTS, Double baseIRRF, Double baseHoraExtra, Double valorProventos, Double valorDescontos) {
        this.codigoSequencial = codigoSequencial;
        this.codigoEmpresa = codigoEmpresa;
        this.codigoEmpregado = codigoEmpregado;
        this.dataCompetencia = dataCompetencia;
        this.baseINSS = baseINSS;
        this.baseExcedenteINSS = baseExcedenteINSS;
        this.baseFGTS = baseFGTS;
        this.baseIRRF = baseIRRF;
        this.baseHoraExtra = baseHoraExtra;
        this.valorProventos = valorProventos;
        this.valorDescontos = valorDescontos;
    }

    public Integer getCodigoSequencial() {
        return codigoSequencial;
    }

    public void setCodigoSequencial(Integer codigoSequencial) {
        this.codigoSequencial = codigoSequencial;
    }

    public EmpresasModel getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(EmpresasModel codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public EmpregadosModel getCodigoEmpregado() {
        return codigoEmpregado;
    }

    public void setCodigoEmpregado(EmpregadosModel codigoEmpregado) {
        this.codigoEmpregado = codigoEmpregado;
    }

    public LocalDate getDataCompetencia() {
        return dataCompetencia;
    }

    public void setDataCompetencia(LocalDate dataCompetencia) {
        this.dataCompetencia = dataCompetencia;
    }

    public Double getBaseINSS() {
        return baseINSS;
    }

    public void setBaseINSS(Double baseINSS) {
        this.baseINSS = baseINSS;
    }

    public Double getBaseExcedenteINSS() {
        return baseExcedenteINSS;
    }

    public void setBaseExcedenteINSS(Double baseExcedenteINSS) {
        this.baseExcedenteINSS = baseExcedenteINSS;
    }

    public Double getBaseFGTS() {
        return baseFGTS;
    }

    public void setBaseFGTS(Double baseFGTS) {
        this.baseFGTS = baseFGTS;
    }

    public Double getBaseIRRF() {
        return baseIRRF;
    }

    public void setBaseIRRF(Double baseIRRF) {
        this.baseIRRF = baseIRRF;
    }

    public Double getBaseHoraExtra() {
        return baseHoraExtra;
    }

    public void setBaseHoraExtra(Double baseHoraExtra) {
        this.baseHoraExtra = baseHoraExtra;
    }

    public Double getValorProventos() {
        return valorProventos;
    }

    public void setValorProventos(Double valorProventos) {
        this.valorProventos = valorProventos;
    }

    public Double getValorDescontos() {
        return valorDescontos;
    }

    public void setValorDescontos(Double valorDescontos) {
        this.valorDescontos = valorDescontos;
    }
}