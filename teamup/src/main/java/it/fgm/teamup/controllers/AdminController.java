package it.fgm.teamup.controllers;


import it.fgm.teamup.model.Admin;
import it.fgm.teamup.model.Progetto;
import it.fgm.teamup.model.Sessione;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.IProgettoRepository;
import it.fgm.teamup.repository.ISessioneRepository;
import it.fgm.teamup.repository.IUtenteRepository;
import it.fgm.teamup.services.IUtenteService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class AdminController {

    String email = "admin@admin.it";
    String pwd = "admin";


    @Autowired
    ISessioneRepository sessioneRepository;

    @Autowired
    IProgettoRepository progettoRepository;

    @Autowired
    IUtenteRepository utenteRepository;

    @GetMapping("/admin")
    public String doGet(@ModelAttribute Admin admin) {

        return "admin";
    }

    @GetMapping("/loginAdmin")
    public String get(Utente utente, ModelMap modelMap,
                      HttpSession session) {
        if (session.getAttribute( "admin" ) == null) {
            modelMap.put( "admin", new Admin() );
            return "loginAdmin";
        } else {
            return "redirect:loginAdmin:?error";
        }
    }


    @PostMapping("/loginAdmin")
    public String loginadmin(@ModelAttribute("admin") Admin admin, ModelMap modelMap,
                             Locale locale, HttpSession session
    ) {

        Sessione s = new Sessione();


        //if(utenteService.findByEmailAndPassword(user.getEmail(), user.getPassword())!=null){
        if (admin.getUsername().equals( email ) && admin.getPassword().equals( pwd )
                && session.getAttribute( "utente" ) == null) {
            return "admin";
        } else {
            return "loginadmin";
        }

    }
}