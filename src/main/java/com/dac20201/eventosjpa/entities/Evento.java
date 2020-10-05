package com.dac20201.eventosjpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Evento {

    public Evento() {
    }

    public Evento(String nome, String sigla, String areaConcentracao, String instituicaoOrganizadora) {
        this.nome = nome;
        this.sigla = sigla;
        this.areaConcentracao = areaConcentracao;
        this.instituicaoOrganizadora = instituicaoOrganizadora;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sigla;
    private String areaConcentracao;
    private String instituicaoOrganizadora;

    public Integer getId() {
        return this.Id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSigla() {
        return this.sigla;
    }

    public String getAreaConcentracao() {
        return this.areaConcentracao;
    }

    public String getInstituicaoOrganizadora() {
        return this.instituicaoOrganizadora;
    }

}
