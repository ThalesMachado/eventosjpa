package com.dac20201.eventosjpa.repositories;

import java.util.ArrayList;

import com.dac20201.eventosjpa.entities.EdicaoEvento;
import com.dac20201.eventosjpa.entities.Evento;

import org.springframework.data.repository.CrudRepository;

public interface EdicaoEventoRepository extends CrudRepository<EdicaoEvento, Integer> {

    <S extends EdicaoEvento> S save(S e);

    ArrayList<EdicaoEvento> findByEvento(Evento evento);

}
