package it.fgm.teamup.controllers;

import it.fgm.teamup.model.*;
import it.fgm.teamup.repository.ISessioneRepository;
import it.fgm.teamup.repository.PartecipazioneRepository;
import it.fgm.teamup.services.IAttivitàService;
import it.fgm.teamup.services.IProgettoService;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AttivitàController {

    @Autowired
    IAttivitàService attivitaService;

    @Autowired
    ISessioneRepository sessioneRepository;


    IProgettoService progettoService;

    @Autowired
    PartecipazioneRepository partecipazioneRepository;

    @GetMapping("/attivita")
    public String get(@ModelAttribute Attivita attivita, BindingResult result, Model model) {

        return "attività";
    }
/*
    @PostMapping("/nuovaAttività")
    public String post(@ModelAttribute Attivita attivita, BindingResult result,
                       HttpSession httpSession) {

        Sessione sessione = new Sessione();

        sessione.setId( httpSession.getId() );

        Partecipazione partecipazione;

        Progetto progetto;


        if (result.hasErrors()) {
            return "attivita";
        }

        Sessione s = new Sessione();

        if (sessioneRepository.findById( sessione.getId() ) != null) {
            s = sessioneRepository.findById( sessione.getId() );
            Utente utente = s.getUtente();
            partecipazione = partecipazioneRepository.findByUtenteId( utente.getId() );
            progetto = partecipazione.getProgetto();
            if (progettoService.findById( progetto.getId() ) != null)
                System.out.println( utente.getId() );
            try {
                httpSession.getAttribute( "attivita" );
            } catch (Exception e) {
                e.printStackTrace();
            }


            Attivita a = attivitaService.save( attivita );

            return "homePage_progetti";

        }
        return "redirect:index";
    }*/
}