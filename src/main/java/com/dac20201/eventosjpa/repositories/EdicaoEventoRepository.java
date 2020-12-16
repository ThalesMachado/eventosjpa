package com.dac20201.eventosjpa.repositories;

import java.sql.Date;
import java.util.ArrayList;

import com.dac20201.eventosjpa.entities.EdicaoEvento;
import com.dac20201.eventosjpa.entities.Evento;

import org.springframework.data.repository.CrudRepository;

public interface EdicaoEventoRepository extends CrudRepository<EdicaoEvento, Integer> {

    <S extends EdicaoEvento> S save(S e);

    ArrayList<EdicaoEvento> findByEvento(Evento evento);

    ArrayList<EdicaoEvento> findByCidadeSede(String cidadeSede);

    ArrayList<EdicaoEvento> findByDataInicioGreaterThanEqual(Date dataInicio);

    ArrayList<EdicaoEvento> findByDataInicioLessThanEqual(Date dataInicio);

    ArrayList<EdicaoEvento> findByDatafimGreaterThanEqual(Date datafim);

    ArrayList<EdicaoEvento> findByDatafimLessThanEqual(Date datafim);

}
