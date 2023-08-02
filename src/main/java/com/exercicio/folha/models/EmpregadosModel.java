package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "Empregados_TB")
public class EmpregadosModel {

    @EmbeddedId
    private EmpregadosPK empregadoID;

    @Column (name = "nome", nullable = false, length = 60)
    private String nomeEmpregado;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column (name = "admissao", nullable = false)
    private LocalDate dataAdmissao;

    @Column (name = "salario")
    private Double salarioEmpregado;

    public EmpregadosModel() {
    }

    public EmpregadosModel(EmpregadosPK empregadoID, String nomeEmpregado, LocalDate dataAdmissao, Double salarioEmpregado) {
        this.empregadoID = empregadoID;
        this.nomeEmpregado = nomeEmpregado;
        this.dataAdmissao = dataAdmissao;
        this.salarioEmpregado = salarioEmpregado;
    }

    public EmpregadosPK getEmpregadoID() {
        return empregadoID;
    }

    public void setEmpregadoID(EmpregadosPK empregadoID) {
        this.empregadoID = empregadoID;
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Double getSalarioEmpregado() {
        return salarioEmpregado;
    }

    public void setSalarioEmpregado(Double salarioEmpregado) {
        this.salarioEmpregado = salarioEmpregado;
    }
}
