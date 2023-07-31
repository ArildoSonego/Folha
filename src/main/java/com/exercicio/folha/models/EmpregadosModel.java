package com.exercicio.folha.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "Empregados_TB",uniqueConstraints = {@UniqueConstraint(columnNames = {"codi_emp","i_empregados"})})
public class EmpregadosModel {

    @ManyToOne
    @JoinColumn (name = "codi_emp", nullable = false)
    private EmpresasModel codigoEmpresa;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name= "i_empregados", nullable = false)
    private Integer codigoEmpregado;

    @Column (name = "nome", nullable = false, length = 60)
    private String nomeEmpregado;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column (name = "admissao", nullable = false)
    private LocalDate dataAdmissao;

    @Column (name = "salario")
    private Double salarioEmpregado;

    public EmpregadosModel() {
    }

    public EmpresasModel getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(EmpresasModel codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public Integer getCodigoEmpregado() {
        return codigoEmpregado;
    }

    public void setCodigoEmpregado(Integer codigoEmpregado) {
        this.codigoEmpregado = codigoEmpregado;
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
