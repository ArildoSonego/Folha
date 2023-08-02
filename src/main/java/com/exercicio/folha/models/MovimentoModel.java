package com.exercicio.folha.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "Movimento_TB")
public class MovimentoModel extends AtividadeModel {

    @Column (name = "prov_desc", nullable = false)
    private Character tipoMovimento;

    @Column (name = "valor_calc", nullable = false)
    private Double valorCalculado;

    public MovimentoModel() {
    }

    public MovimentoModel(AtividadePK codigoAtividade, Double valorInformado, Character tipoMovimento, Double valorCalculado) {
        super(codigoAtividade, valorInformado);
        this.tipoMovimento = tipoMovimento;
        this.valorCalculado = valorCalculado;
    }

    public Character getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(Character tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Double getValorCalculado() {
        return valorCalculado;
    }

    public void setValorCalculado(Double valorCalculado) {
        this.valorCalculado = valorCalculado;
    }
}
