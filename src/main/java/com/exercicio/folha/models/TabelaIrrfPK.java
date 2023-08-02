package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.util.Objects;


@Embeddable
public class TabelaIrrfPK {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "i_tabcalcirrf", nullable = false)
    private LocalDate codigoTabelaIRRF;

    @Column(name = "valor_ate")
    private Double valorLimite;

    public TabelaIrrfPK() {
    }

    public TabelaIrrfPK(LocalDate codigoTabelaIRRF, Double valorLimite) {
        this.codigoTabelaIRRF = codigoTabelaIRRF;
        this.valorLimite = valorLimite;
    }

    public LocalDate getCodigoTabelaIRRF() {
        return codigoTabelaIRRF;
    }

    public void setCodigoTabelaIRRF(LocalDate codigoTabelaIRRF) {
        this.codigoTabelaIRRF = codigoTabelaIRRF;
    }

    public Double getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(Double valorLimite) {
        this.valorLimite = valorLimite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TabelaIrrfPK that)) return false;
        return Objects.equals(getCodigoTabelaIRRF(), that.getCodigoTabelaIRRF()) && Objects.equals(getValorLimite(), that.getValorLimite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoTabelaIRRF(), getValorLimite());
    }
}