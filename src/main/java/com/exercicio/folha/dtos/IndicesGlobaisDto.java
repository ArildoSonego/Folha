package com.exercicio.folha.dtos;

import java.util.Objects;

public class IndicesGlobaisDto {
      private  double indiceFGTS;
      private double indiceINSS;
      private double indiceIRRF;
      private double excedenteINSS;

    public IndicesGlobaisDto() {
    }

    public IndicesGlobaisDto(double indiceFGTS, double indiceINSS, double indiceIRRF, double excedenteINSS) {
        this.indiceFGTS = indiceFGTS;
        this.indiceINSS = indiceINSS;
        this.indiceIRRF = indiceIRRF;
        this.excedenteINSS = excedenteINSS;
    }

    public double getIndiceFGTS() {
        return indiceFGTS;
    }

    public void setIndiceFGTS(double indiceFGTS) {
        this.indiceFGTS = indiceFGTS;
    }

    public double getIndiceINSS() {
        return indiceINSS;
    }

    public void setIndiceINSS(double indiceINSS) {
        this.indiceINSS = indiceINSS;
    }

    public double getIndiceIRRF() {
        return indiceIRRF;
    }

    public void setIndiceIRRF(double indiceIRRF) {
        this.indiceIRRF = indiceIRRF;
    }

    public double getExcedenteINSS() {
        return excedenteINSS;
    }

    public void setExcedenteINSS(double excedenteINSS) {
        this.excedenteINSS = excedenteINSS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndicesGlobaisDto that)) return false;
        return Double.compare(that.getIndiceFGTS(), getIndiceFGTS()) == 0 && Double.compare(that.getIndiceINSS(), getIndiceINSS()) == 0 && Double.compare(that.getIndiceIRRF(), getIndiceIRRF()) == 0 && Double.compare(that.getExcedenteINSS(), getExcedenteINSS()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndiceFGTS(), getIndiceINSS(), getIndiceIRRF(), getExcedenteINSS());
    }
}
