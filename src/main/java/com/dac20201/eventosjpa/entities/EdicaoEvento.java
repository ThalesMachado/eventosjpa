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
}
