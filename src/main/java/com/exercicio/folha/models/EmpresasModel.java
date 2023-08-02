package com.exercicio.folha.models;

import jakarta.persistence.*;

@Entity
@Table(name= "Empresas_TB")
public class EmpresasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column (name = "codi_emp")
    private Integer codigoEmpresa;

    @Column(name = "nome_emp", nullable = false, unique = true, length = 60)
    private String nomeEmpresa;

    public EmpresasModel() {
    }

    public EmpresasModel(Integer codigoEmpresa, String nomeEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
        this.nomeEmpresa = nomeEmpresa;
    }

    public Integer getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Integer codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }
}