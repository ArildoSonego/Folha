package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@MappedSuperclass
public abstract class AtividadeModel {

    @Column (name = "codi_emp", nullable = false)
    private Integer codigoEmpresa;

    @Id
    @Column (name = "i_empregados", nullable = false)
    private Integer codigoEmpregado;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column (name = "competencia", nullable = false)
    private LocalDate dataCompetencia;

    @Column (name = "i_eventos", nullable = false)
    private Integer codigoEvento;

    @Column (name = "valor_inf", nullable = false)
    private Double valorInformado;

    public AtividadeModel() {
    }

    public AtividadeModel(Integer codigoEmpresa, Integer codigoEmpregado, LocalDate dataCompetencia, Integer codigoEvento, Double valorInformado) {
        this.codigoEmpresa = codigoEmpresa;
        this.codigoEmpregado = codigoEmpregado;
        this.dataCompetencia = dataCompetencia;
        this.codigoEvento = codigoEvento;
        this.valorInformado = valorInformado;
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

    public Double getValorInformado() {
        return valorInformado;
    }

    public void setValorInformado(Double valorInformado) {
        this.valorInformado = valorInformado;
    }
}
