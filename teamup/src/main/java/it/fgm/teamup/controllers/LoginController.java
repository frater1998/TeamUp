package it.fgm.teamup.controllers;


import it.fgm.teamup.exception.UtenteNonTrovato;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.IUtenteRepository;
import it.fgm.teamup.services.IUtenteService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String get(@ModelAttribute Utente utente, BindingResult result, Model model) {

        return "login";
    }

    @PostMapping("/login")
    public String post(@ModelAttribute Utente utente, Model model) throws UtenteNonTrovato {

        IUtenteService utenteService = null;

        IUtenteRepository utenteRepository = null;


        Utente userLogin = new Utente();

        userLogin.setEmail( userLogin.getEmail() );
        userLogin.setPassword( userLogin.getPassword() );


        String email = userLogin.getEmail();
        String password = userLogin.getPassword();

        model.addAttribute( "email", userLogin.getEmail() );
        model.addAttribute( "password", userLogin.getPassword() );



        Utente passDb = utenteService.findByPassword(password);
        Utente emailDb =  utenteService.findByEmail( email );


       ;


        if (email.equals( emailDb )) {

            if (password.equals( passDb )) {

                return "profiloUtente";

            }
        }
        return "redirect:login";
    }

}
