package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class BaseServicosPK {

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="codi_emp"),
            @JoinColumn(name="i_empregados")
    })
    private EmpregadosModel codigoEmpregado;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "competencia", nullable = false)
    private LocalDate dataCompetencia;

    public BaseServicosPK() {
    }

    public BaseServicosPK(EmpregadosModel codigoEmpregado, LocalDate dataCompetencia) {
        this.codigoEmpregado = codigoEmpregado;
        this.dataCompetencia = dataCompetencia;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseServicosPK that)) return false;
        return Objects.equals(getCodigoEmpregado(), that.getCodigoEmpregado()) && Objects.equals(getDataCompetencia(), that.getDataCompetencia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoEmpregado(), getDataCompetencia());
    }
}
