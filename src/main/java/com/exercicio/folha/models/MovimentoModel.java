package com.exercicio.folha.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "Movimento_TB", uniqueConstraints = {@UniqueConstraint(columnNames ={"codi_emp", "i_empregados", "competencia","i_eventos" })})
public class MovimentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_sequencial")
    private Integer sequencial;

    @Column(name = "codi_emp", nullable = false)
    private Integer codigoEmpresa;

    @Column(name = "i_empregados", nullable = false)
    private Integer codigoEmpregado;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "competencia", nullable = false)
    private LocalDate dataCompetencia;

    @Column(name = "i_eventos", nullable = false)
    private Integer codigoEvento;

    @Column(name = "prov_desc", nullable = false)
    private Character tipoMovimento;
    @Column(name = "valor_inf", nullable = false)
    private Double valorInformado;

    @Column(name = "valor_calc", nullable = false)
    private Double valorCalculado;

    public MovimentoModel() {
    }

    public MovimentoModel(Integer sequencial, Integer codigoEmpresa, Integer codigoEmpregado, LocalDate dataCompetencia, Integer codigoEvento, Character tipoMovimento, Double valorInformado, Double valorCalculado) {
        this.sequencial = sequencial;
        this.codigoEmpresa = codigoEmpresa;
        this.codigoEmpregado = codigoEmpregado;
        this.dataCompetencia = dataCompetencia;
        this.codigoEvento = codigoEvento;
        this.tipoMovimento = tipoMovimento;
        this.valorInformado = valorInformado;
        this.valorCalculado = valorCalculado;
    }

    public Integer getSequencial() {
        return sequencial;
    }

    public void setSequencial(Integer sequencial) {
        this.sequencial = sequencial;
    }

    public Integer getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Integer codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public Integer getCodigoEmpregado() {
        return codigoEmpregado;
    }

    public void setCodigoEmpregado(Integer codigoEmpregado) {
        this.codigoEmpregado = codigoEmpregado;
    }

    public LocalDate getDataCompetencia() {
        return dataCompetencia;
    }

    public void setDataCompetencia(LocalDate dataCompetencia) {
        this.dataCompetencia = dataCompetencia;
    }

    public Integer getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(Integer codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public Character getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(Character tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Double getValorInformado() {
        return valorInformado;
    }

    public void setValorInformado(Double valorInformado) {
        this.valorInformado = valorInformado;
    }

    public Double getValorCalculado() {
        return valorCalculado;
    }

    public void setValorCalculado(Double valorCalculado) {
        this.valorCalculado = valorCalculado;
    }
}