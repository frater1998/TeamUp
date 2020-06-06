package it.fgm.teamup.controllers;


import it.fgm.teamup.model.Utente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UtenteController {

    @PostMapping("/modificaDatiUtente")
    public String updateUser(@ModelAttribute  Utente utente, BindingResult result, Model model){




        return "profiloUtente";

    }


}
