package com.dac20201.eventosjpa;

import com.dac20201.eventosjpa.entities.EdicaoEvento;

import org.springframework.data.repository.CrudRepository;

public interface EdicaoEventoRepository extends CrudRepository<EdicaoEvento, Integer> {

    <S extends EdicaoEvento> S save(S e);

}
