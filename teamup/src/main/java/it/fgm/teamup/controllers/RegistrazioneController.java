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

import javax.validation.Valid;
import java.util.Date;

@Controller
public class RegistrazioneController {

    @Autowired
    IUtenteService userService;


    @GetMapping("/registrati")
    public String get(@ModelAttribute @Valid Utente utente, BindingResult result, Model model) {

        return "registrati";
    }

    @PostMapping("/registrati")
    public String post(@ModelAttribute  Utente utente, BindingResult result, Model model)  {

      /*  if(result.hasErrors()){
            return "registrati";
        }

       */


        utente.setEmail(utente.getEmail());
        utente.setPassword(utente.getPassword());
        utente.setNome(utente.getNome());
        utente.setCognome(utente.getCognome());
        utente.setDataNascita(utente.getDataNascita());



        model.addAttribute("email", utente.getEmail());
        model.addAttribute("password", utente.getPassword());
        model.addAttribute("nome", utente.getNome());
        model.addAttribute("cognome", utente.getCognome());



        Utente u = userService.salva(utente);




        return "index";
   // }

    //viene richiamato prima di ogni request handler all'interno del controller
    //i request handler sono tutti i metodi mappati nel controller con @getMapping
   /* @ModelAttribute
    public void setWelcomeMsg(Model model) {
        model.addAttribute("welcomeMsg","Ciao Fernando!!!!");*/

        //return "registrati";
    }


}
