package com.dac20201.eventosjpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dac20201.eventosjpa.entities.EdicaoEvento;
import com.dac20201.eventosjpa.entities.Evento;
import com.dac20201.eventosjpa.repositories.EdicaoEventoRepository;
import com.dac20201.eventosjpa.repositories.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventoRestControllerV1 {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EdicaoEventoRepository edicaoEventoRepository;

    @GetMapping(value = { "v1/eventos", "v1/evento/index", "v1/evento/all" })
    public Iterable<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @GetMapping("v1/evento/{id}")
    public Evento findEvento(@PathVariable("id") Integer id) {
        return eventoRepository.findById(id).get();
    }

    @GetMapping("v1/evento/{id}/edicoes")
    public List<EdicaoEvento> findEventosByEdicao(@PathVariable("id") Integer id) {
        Evento ev = eventoRepository.findById(id).get();
        return edicaoEventoRepository.findByEvento(ev);
    }

    @PostMapping("v1/evento")
    public Evento criaEvento(@RequestBody Evento evento) {
        Evento eventoCriado = eventoRepository.save(evento);
        return eventoCriado;
    }

    @PutMapping("v1/evento/")
    public Evento atualizaEvento(@RequestBody Evento payload) {
        Evento ev = eventoRepository.findById(payload.getId()).get();

        if (ev.getInstituicaoOrganizadora() != payload.getInstituicaoOrganizadora())
            ev.setInstituicaoOrganizadora(ev.getInstituicaoOrganizadora());
        else if (ev.getNome() != payload.getNome())
            ev.setNome(ev.getNome());
        else if (ev.getSigla() != payload.getSigla())
            ev.setSigla(ev.getSigla());

        eventoRepository.save(ev);

        return ev;
    }

    @DeleteMapping("v1/evento/{id}")
    public Map<String, String> excluiEvento(@PathVariable("id") Integer idEvento) {
        Map<String, String> response = new HashMap<String, String>();
        response.put("message", "ok");
        response.put("status", "os");
        return response;
    }

}
