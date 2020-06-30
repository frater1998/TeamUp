package it.fgm.teamup.controllers;

import it.fgm.teamup.model.Attivita;
import it.fgm.teamup.repository.IAttivitàRepository;
import it.fgm.teamup.services.IAttivitàService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ModificaAttivitàController {

    @Autowired
    IAttivitàService attivitaService;

    @Autowired
    IAttivitàRepository attivitàRepository;

    @GetMapping("/modificaAttività")
    public String modificaAttività() {

        return "modificaAttività";
    }

    @PostMapping("/modificaAttività/{id}")
    public String updateAttività(@PathVariable("id") int id, Attivita attivita,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            attivita.setId( id );
            return "modificaAttività";
        }
        attivitàRepository.save( attivita );
        return "redirect:/progetto";
    }
}