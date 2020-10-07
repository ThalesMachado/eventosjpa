package com.dac20201.eventosjpa.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.dac20201.eventosjpa.repositories.EdicaoEventoRepository;
import com.dac20201.eventosjpa.repositories.EventoRepository;
import com.dac20201.eventosjpa.entities.EdicaoEvento;
import com.dac20201.eventosjpa.entities.Evento;

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
@RequestMapping("/edicaoevento")
public class EdicaoEventoController {

    private class EdicaoEventoWrapper {

        public String nomeEvento;
        public Integer eventoId;
        public String numero;
        public String ano;
        public Date dataInicio;
        public Date datafim;
        public String cidadeSede;
        public String estado;
        public Integer Id;

        public EdicaoEventoWrapper(String nomeEvento, String numero, String ano, Date dataInicio, Date datafim,
                String cidadeSede, String estado, Integer id, Integer eventoId) {
            this.nomeEvento = nomeEvento;
            this.numero = numero;
            this.ano = ano;
            this.dataInicio = dataInicio;
            this.datafim = datafim;
            this.estado = estado;
            this.Id = id;
            this.eventoId = eventoId;
        }
    }

    @Autowired
    private EdicaoEventoRepository edicaoEventoRepository;
    @Autowired
    private EventoRepository eventoRepository;

    @RequestMapping(value = { "", "/index" })
    public String index(Model modelo) {
        Iterable<EdicaoEvento> edicoes = edicaoEventoRepository.findAll();
        List<EdicaoEventoWrapper> edicoesWrapper = new ArrayList<EdicaoEventoWrapper>();
        for (EdicaoEvento ee : edicoes) {
            EdicaoEventoWrapper eew = new EdicaoEventoWrapper(ee.getEvento().getNome(), ee.getNumero(), ee.getAno(),
                    ee.getDataInicio(), ee.getDatafim(), ee.getCidadeSede(), ee.getEstado(), ee.getId(),
                    ee.getEvento().getId());
            edicoesWrapper.add(eew);
        }
        modelo.addAttribute("edicoes", edicoesWrapper);
        return "EdicaoEventoTemplates/index";
    }

    @RequestMapping("/novo")
    public String novaEdicaoEvento(Model modelo) {
        Iterable<Evento> eventos = eventoRepository.findAll();
        modelo.addAttribute("eventos", eventos);
        return "EdicaoEventoTemplates/novo";
    }

    @PostMapping("/criar")
    public RedirectView criarEdicaoEvento(Model modelo, @RequestParam("numero") String numero,
            @RequestParam("ano") String ano, @RequestParam("dataInicio") Date dataInicio,
            @RequestParam("datafim") Date dataFim, @RequestParam("cidadeSede") String cidadeSede,
            @RequestParam("estado") String estado, @RequestParam("evento") Integer eventoId) {
        Evento evento = eventoRepository.findById(eventoId).get();
        EdicaoEvento edicaoEvento = new EdicaoEvento(evento, numero, ano, dataInicio, dataFim, cidadeSede, estado);
        edicaoEventoRepository.save(edicaoEvento);
        return new RedirectView(edicaoEvento.getId().toString());
    }

    @GetMapping("/{id}")
    public String edicaoEvento(@PathVariable("id") Integer id, Model modelo) {
        EdicaoEvento ee = edicaoEventoRepository.findById(id).get();
        EdicaoEventoWrapper eew = new EdicaoEventoWrapper(ee.getEvento().getNome(), ee.getNumero(), ee.getAno(),
                ee.getDataInicio(), ee.getDatafim(), ee.getCidadeSede(), ee.getEstado(), ee.getId(),
                ee.getEvento().getId());
        modelo.addAttribute("edicao", eew);
        return "EdicaoEventoTemplates/edicaoevento";
    }

    @GetMapping("/excluir/{id}")
    public RedirectView excluirEdicaoEvento(@PathVariable("id") Integer id) {
        edicaoEventoRepository.deleteById(id);
        return new RedirectView("/");
    }

}
