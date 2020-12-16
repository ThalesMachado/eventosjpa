package com.dac20201.eventosjpa.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EdicaoEvento {

    public EdicaoEvento() {
    }

    public EdicaoEvento(Evento evento, String numero, String ano, Date dataInicio, Date datafim, String cidadeSede,
            String estado) {
        this.evento = evento;
        this.numero = numero;
        this.ano = ano;
        this.dataInicio = dataInicio;
        this.datafim = datafim;
        this.estado = estado;
        this.cidadeSede = cidadeSede;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @ManyToOne
    private Evento evento;
    @Column(nullable = false)
    private String numero;
    private String ano;
    private Date dataInicio;
    private Date datafim;
    private String cidadeSede;
    private String estado;

    public Integer getId() {
        return this.Id;
    }

    public Evento getEvento() {
        return this.evento;
    }

    public String getNumero() {
        return this.numero;
    }

    public String getAno() {
        return this.ano;
    }

    public Date getDataInicio() {
        return this.dataInicio;
    }

    public Date getDatafim() {
        return this.datafim;
    }

    public String getCidadeSede() {
        return this.cidadeSede;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setCidadeSede(String cidadeSede) {
        this.cidadeSede = cidadeSede;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
