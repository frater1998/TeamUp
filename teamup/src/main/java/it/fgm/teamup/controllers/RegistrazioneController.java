package it.fgm.teamup.controllers;



import it.fgm.teamup.model.Utente;
import it.fgm.teamup.services.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Date;

@Controller
public class RegistrazioneController {

    @Autowired
    IUtenteService userService;


    @GetMapping("/registrati")
    public String get(@ModelAttribute  Utente utente, BindingResult result, Model model) {

        return "registrati";
    }

    @PostMapping("/registrati")
    public String post(@ModelAttribute  Utente utente, BindingResult result)  {

         if(result.hasErrors()){
            return "registrati";
        }

        utente.setNome(utente.getNome());
        utente.setCognome(utente.getCognome());
        utente.setEmail(utente.getEmail());
        utente.setPassword(utente.getPassword());


        Utente u = userService.salva(utente);

        return "login";

    }


}
