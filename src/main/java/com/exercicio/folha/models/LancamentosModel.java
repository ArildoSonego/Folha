package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "Lancamentos_TB")
public class LancamentosModel extends AtividadeModel{

    public LancamentosModel() {
    }

    public LancamentosModel(Integer codigoEmpresa, Integer codigoEmpregado, LocalDate dataCompetencia, Integer codigoEvento, Double valorInformado) {
        super(codigoEmpresa, codigoEmpregado, dataCompetencia, codigoEvento, valorInformado);
    }
}
