package com.dac20201.eventosjpa.DAO;

import com.dac20201.eventosjpa.EventoRepository;
import com.dac20201.eventosjpa.entities.Evento;

import org.springframework.beans.factory.annotation.Autowired;

public class EventoDAO {
    @Autowired
    private EventoRepository repository;

    public EventoDAO() {
    }

    public static EventoDAO getInstance() {
        return new EventoDAO();
    }

    public String GetNomeEvento(Integer eventoid) {
        Evento evento = repository.findById(eventoid).get();
        return evento.getNome();
    }
}
