package com.exercicio.folha.models;

import jakarta.persistence.*;

import java.util.Objects;

@Embeddable
public class EventosPK {

    @ManyToOne
    @JoinColumn(name = "codi_emp", nullable = false)
    private EmpresasModel codigoEmpresa;

    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column (name= "i_eventos", nullable = false)
    private Integer codigoEvento;

    public EventosPK() {
    }

    public EventosPK(EmpresasModel codigoEmpresa, Integer codigoEvento) {
        this.codigoEmpresa = codigoEmpresa;
        this.codigoEvento = codigoEvento;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventosPK eventosPK)) return false;
        return Objects.equals(getCodigoEmpresa(), eventosPK.getCodigoEmpresa()) && Objects.equals(getCodigoEvento(), eventosPK.getCodigoEvento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoEmpresa(), getCodigoEvento());
    }
}
