package com.dac20201.eventosjpa;

import com.dac20201.eventosjpa.entities.Evento;

import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, Integer> {

    <S extends Evento> S save(S e);

}
