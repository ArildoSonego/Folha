package com.exercicio.folha.models;

import jakarta.persistence.*;

import java.util.Objects;

@Embeddable
public class EmpregadosPK {

    @ManyToOne
    @JoinColumn(name = "codi_emp", nullable = false)
    private EmpresasModel codigoEmpresa;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column (name= "i_empregados", nullable = false)
    private Integer codigoEmpregado;

    public EmpregadosPK() {
    }

    public EmpregadosPK(EmpresasModel codigoEmpresa, Integer codigoEmpregado) {
        this.codigoEmpresa = codigoEmpresa;
        this.codigoEmpregado = codigoEmpregado;
    }

    public EmpresasModel getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(EmpresasModel codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public Integer getCodigoEmpregado() {
        return codigoEmpregado;
    }

    public void setCodigoEmpregado(Integer codigoEmpregado) {
        this.codigoEmpregado = codigoEmpregado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmpregadosPK that)) return false;
        return Objects.equals(getCodigoEmpresa(), that.getCodigoEmpresa()) && Objects.equals(getCodigoEmpregado(), that.getCodigoEmpregado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoEmpresa(), getCodigoEmpregado());
    }
}
