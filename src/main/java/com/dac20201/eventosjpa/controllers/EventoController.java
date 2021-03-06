package com.dac20201.eventosjpa.controllers;

import java.util.List;

import com.dac20201.eventosjpa.repositories.EdicaoEventoRepository;
import com.dac20201.eventosjpa.repositories.EventoRepository;
import com.dac20201.eventosjpa.entities.Evento;
import com.dac20201.eventosjpa.entities.EdicaoEvento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoRepository repository;
    @Autowired
    private EdicaoEventoRepository edicaoEventoRepository;

    @RequestMapping(value = { "/index", "" })
    public String eventosIndex(Model modelo) {
        Iterable<Evento> lstEventos = repository.findAll();
        modelo.addAttribute("eventos", lstEventos);

        return "EventosTemplates/index.html";
    }

    @GetMapping("/novo")
    public String novoEvento() {
        return "EventosTemplates/novo";
    }

    @PostMapping("/criar")
    public RedirectView criarEvento(@RequestParam("nome") String nome, @RequestParam("sigla") String sigla,
            @RequestParam("areaConcentracao") String areaConcentracao,
            @RequestParam("instituicaoOrganizadora") String instituicaoOrganizadora) {
        Evento evento = new Evento(nome, sigla, areaConcentracao, instituicaoOrganizadora);
        evento = repository.save(evento);
        return new RedirectView(String.format("%d", evento.getId()));
    }

    @GetMapping("/{id}")
    public String evento(@PathVariable("id") Integer id, Model modelo) {
        Evento evento = repository.findById(id).get();
        List<EdicaoEvento> edicoes = edicaoEventoRepository.findByEvento(evento);
        if (!edicoes.isEmpty())
            modelo.addAttribute("edicoes", edicoes);
        modelo.addAttribute("nome", evento.getNome());
        modelo.addAttribute("evento", evento);
        return "EventosTemplates/evento";
    }

    @GetMapping("excluir/{id}")
    public RedirectView excluirEventoEdicoes(@PathVariable("id") Integer id) {
        Evento evento = repository.findById(id).get();
        List<EdicaoEvento> edicoes = edicaoEventoRepository.findByEvento(evento);
        if (edicoes.size() > 0)
            edicaoEventoRepository.deleteAll(edicoes);
        repository.delete(evento);

        return new RedirectView("/");

    }

    @GetMapping(value = "busca")
    public String getMethodName(@RequestParam("termo") String termo, Model modelo) {
        List<Evento> eventos = repository.findByNomeContaining(termo);
        modelo.addAttribute("eventos", eventos);
        return "EventosTemplates/resultadoPesquisa";
    }

}
