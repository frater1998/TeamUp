package it.fgm.teamup.controllers;

import it.fgm.teamup.model.Attivita;
import it.fgm.teamup.services.IAttivitàService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AttivitàController {

    @Autowired
    IAttivitàService attivitaService;

    @GetMapping("/attivita")
    public String get(@ModelAttribute Attivita attivita, BindingResult result, Model model) {

        return "attività";
    }

    @PostMapping("/attività")
    public String post(@ModelAttribute  Attivita attivita, BindingResult result)  {

        if(result.hasErrors()){
            return "attivita";
        }

        attivita.setObiettivi(attivita.getObiettivi());
        attivita.setPercentualeCompletamento(attivita.getPercentualeCompletamento());

        Attivita a = attivitaService.salva(attivita);

        return "homePage_progetti";
       }
}