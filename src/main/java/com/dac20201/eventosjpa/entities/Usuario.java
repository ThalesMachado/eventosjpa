package com.dac20201.eventosjpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario {

    public Usuario(String nome, String alias, String email, String senha) {
        this.nome = nome;
        this.alias = alias;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer usuarioId;
    private String nome;
    private String alias;
    private String email;
    @JsonIgnore
    private String senha;

    public String getNome() {
        return this.nome;
    }

    public String getAlias() {
        return this.alias;
    }

    public String getEmail() {
        return this.email;
    }

    public Boolean validaSenha(String textoAsClaras) {
        return true;
    }

}
