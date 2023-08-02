package com.exercicio.folha.models;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class AtividadeModel {

    @EmbeddedId
    private AtividadePK codigoAtividade;

    @Column (name = "valor_inf", nullable = false)
    private Double valorInformado;

    public AtividadeModel() {
    }

    public AtividadeModel(AtividadePK codigoAtividade, Double valorInformado) {
        this.codigoAtividade = codigoAtividade;
        this.valorInformado = valorInformado;
    }

    public AtividadePK getCodigoAtividade() {
        return codigoAtividade;
    }

    public void setCodigoAtividade(AtividadePK codigoAtividade) {
        this.codigoAtividade = codigoAtividade;
    }

    public Double getValorInformado() {
        return valorInformado;
    }

    public void setValorInformado(Double valorInformado) {
        this.valorInformado = valorInformado;
    }
}
