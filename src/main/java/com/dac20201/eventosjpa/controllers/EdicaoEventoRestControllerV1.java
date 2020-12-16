package com.dac20201.eventosjpa.controllers;

import java.util.HashMap;
import java.util.Map;

import com.dac20201.eventosjpa.entities.EdicaoEvento;
import com.dac20201.eventosjpa.entities.Evento;
import com.dac20201.eventosjpa.repositories.EdicaoEventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EdicaoEventoRestControllerV1 {
    @Autowired
    private EdicaoEventoRepository edicaoEventoRepository;

    @GetMapping(value = { "v1/edicaoeventos", "v1/edicaoevento/index", "v1/edicaoevento/all" })
    public Iterable<EdicaoEvento> findAll() {
        return edicaoEventoRepository.findAll();
    }

    @GetMapping(value = { "v1/buscaredicoesdata" })
    public Iterable<EdicaoEvento> findEdicoesData() {
        return edicaoEventoRepository.findAll();
    }

    @GetMapping("v1/edicaoevento/{id}")
    public EdicaoEvento findEdicaoEvento(@PathVariable("id") Integer id) {
        return edicaoEventoRepository.findById(id).get();
    }

    @PostMapping("v1/edicaoevento")
    public EdicaoEvento criaEdicaoEvento(@RequestBody EdicaoEvento edicaoEvento) {
        EdicaoEvento EdicaoEventoCriado = edicaoEventoRepository.save(edicaoEvento);
        return EdicaoEventoCriado;
    }

    @PutMapping("v1/edicaoevento/")
    public EdicaoEvento atualizaEvento(@RequestBody EdicaoEvento payload) {
        EdicaoEvento ev = edicaoEventoRepository.findById(payload.getId()).get();

        if (ev.getAno() != payload.getAno())
            ev.setAno(ev.getAno());
        else if (ev.getCidadeSede() != payload.getCidadeSede())
            ev.setCidadeSede(ev.getCidadeSede());
        else if (ev.getEvento() != payload.getEvento())
            ev.setEvento(ev.getEvento());
        else if (ev.getNumero() != payload.getNumero())
            ev.setNumero(ev.getNumero());
        else if (ev.getDataInicio() != payload.getDataInicio())
            ev.setDataInicio(ev.getDataInicio());
        else if (ev.getDatafim() != payload.getDatafim())
            ev.setDatafim(ev.getDatafim());
        else if (ev.getEstado() != payload.getEstado())
            ev.setEstado(ev.getEstado());

        edicaoEventoRepository.save(ev);

        return ev;
    }

    @DeleteMapping("v1/edicaoevento/{id}")
    public Map<String, String> excluiEvento(@PathVariable("id") Integer idEvento) {
        edicaoEventoRepository.deleteById(idEvento);
        Map<String, String> response = new HashMap<String, String>();
        response.put("message", "ok");
        response.put("status", "os");
        return response;
    }
}
