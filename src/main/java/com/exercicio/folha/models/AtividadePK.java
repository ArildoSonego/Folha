package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class AtividadePK {

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="codi_emp"),
            @JoinColumn(name="i_empregados")
    })
    private EmpregadosModel codigoEmpregado;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column (name = "competencia", nullable = false)
    private LocalDate dataCompetencia;

    @Column(name="i_eventos", nullable = false)
    private Integer codigoEvento;

    public AtividadePK() {
    }

    public AtividadePK(EmpregadosModel codigoEmpregado, LocalDate dataCompetencia, Integer codigoEvento) {
        this.codigoEmpregado = codigoEmpregado;
        this.dataCompetencia = dataCompetencia;
        this.codigoEvento = codigoEvento;
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

    public Integer getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(Integer codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AtividadePK that)) return false;
        return Objects.equals(getCodigoEmpregado(), that.getCodigoEmpregado()) && Objects.equals(getDataCompetencia(), that.getDataCompetencia()) && Objects.equals(getCodigoEvento(), that.getCodigoEvento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoEmpregado(), getDataCompetencia(), getCodigoEvento());
    }
}
