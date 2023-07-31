package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table (name = "Movimento_TB")
public class MovimentoModel extends AtividadeModel {

    @Column (name = "prov_desc", nullable = false)
    private Character tipoMovimento;

    @Column (name = "valor_calc", nullable = false)
    private Double valorCalculado;

    public MovimentoModel() {
    }

    public MovimentoModel(Integer codigoEmpresa, Integer codigoEmpregado, LocalDate dataCompetencia, Integer codigoEvento, Double valorInformado, Character tipoMovimento, Double valorCalculado) {
        super(codigoEmpresa, codigoEmpregado, dataCompetencia, codigoEvento, valorInformado);
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
