package com.exercicio.folha.models;

import jakarta.persistence.*;

@Entity
@Table (name = "BaseServicos_TB")

public class BaseServicosModel {

    @EmbeddedId
    private BaseServicosPK baseServicoId;

    @Column (name = "base_inss")
    private Double baseINSS;

    @Column (name = "base_excedente_inss")
    private Double baseExcedenteINSS;

    @Column (name = "base_fgts")
    private Double baseFGTS;

    @Column (name = "base_irrf")
    private Double baseIRRF;

    @Column (name = "base_hextra")
    private Double baseHoraExtra;

    @Column (name = "proventos")
    private Double valorProventos;

    @Column (name = "descontos")
    private Double valorDescontos;

    public BaseServicosModel() {
    }

    public BaseServicosModel(BaseServicosPK baseServicoId, Double baseINSS, Double baseExcedenteINSS, Double baseFGTS, Double baseIRRF, Double baseHoraExtra, Double valorProventos, Double valorDescontos) {
        this.baseServicoId = baseServicoId;
        this.baseINSS = baseINSS;
        this.baseExcedenteINSS = baseExcedenteINSS;
        this.baseFGTS = baseFGTS;
        this.baseIRRF = baseIRRF;
        this.baseHoraExtra = baseHoraExtra;
        this.valorProventos = valorProventos;
        this.valorDescontos = valorDescontos;
    }

    public void setBaseServicoId(BaseServicosPK baseServicoId) {
        this.baseServicoId = baseServicoId;
    }

    public void setBaseINSS(Double baseINSS) {
        this.baseINSS = baseINSS;
    }

    public void setBaseExcedenteINSS(Double baseExcedenteINSS) {
        this.baseExcedenteINSS = baseExcedenteINSS;
    }

    public void setBaseFGTS(Double baseFGTS) {
        this.baseFGTS = baseFGTS;
    }

    public void setBaseIRRF(Double baseIRRF) {
        this.baseIRRF = baseIRRF;
    }

    public void setBaseHoraExtra(Double baseHoraExtra) {
        this.baseHoraExtra = baseHoraExtra;
    }

    public void setValorProventos(Double valorProventos) {
        this.valorProventos = valorProventos;
    }

    public void setValorDescontos(Double valorDescontos) {
        this.valorDescontos = valorDescontos;
    }
}