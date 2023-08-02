package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;


@Embeddable
public class TabelaInssPK {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "i_tabcalcinss", nullable = false)
    private LocalDate codigoTabelaINSS;

    @Column(name = "valor_ate")
    private Double valorLimite;

    public TabelaInssPK() {
    }

    public TabelaInssPK(LocalDate codigoTabelaINSS, Double valorLimite) {
        this.codigoTabelaINSS = codigoTabelaINSS;
        this.valorLimite = valorLimite;
    }

    public LocalDate getCodigoTabelaINSS() {
        return codigoTabelaINSS;
    }

    public void setCodigoTabelaINSS(LocalDate codigoTabelaINSS) {
        this.codigoTabelaINSS = codigoTabelaINSS;
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
        if (!(o instanceof TabelaInssPK that)) return false;
        return Objects.equals(getCodigoTabelaINSS(), that.getCodigoTabelaINSS()) && Objects.equals(getValorLimite(), that.getValorLimite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoTabelaINSS(), getValorLimite());
    }
}