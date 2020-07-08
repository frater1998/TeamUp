package it.fgm.teamup.controllers;

import it.fgm.teamup.model.Sessione;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.ISessioneRepository;
import it.fgm.teamup.repository.PartecipazioneRepository;
import it.fgm.teamup.services.ISessioneService;
import it.fgm.teamup.services.IUtenteService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Locale;


@Controller
public class LoginController {

    public String sessionId;


    ISessioneService sessioneService;

    @Autowired
    ISessioneRepository sessioneRepository;

    @Autowired
    IUtenteService utenteService;

    @Autowired
    PartecipazioneRepository partecipazioneRepository;


    SessionFactory sessionFactory;

@GetMapping("/login")
public String get(Utente utente, ModelMap modelMap,
                  HttpSession session) {
    if (session.getAttribute( "utente" ) == null) {
        modelMap.put( "utente", new Utente() );
        return "login";
    } else {
        return "redirect:login:?error";
    }

}

    @PostMapping("/login")
    public String post(@ModelAttribute("utente") Utente user, ModelMap modelMap,
                       Locale locale, HttpSession session
                       ) {

    Sessione s = new Sessione();


        //if(utenteService.findByEmailAndPassword(user.getEmail(), user.getPassword())!=null){
    if (utenteService.findByEmailAndPassword(user.getEmail(), user.getPassword())!= null
            && session.getAttribute( "utente" )== null){

       Session ses = (Session) session.getAttribute( "utente" );
        try {
            //Query query = ses.createQuery( sql );
           // query.setParameter( 0, user.getEmail() );
           // query.setParameter( 1, user.getPassword() );
            user  =  utenteService.findByEmailAndPassword(user.getEmail(), user.getPassword());
           s.setId(  sessionId = session.getId());

        } catch (Exception e){
           // tx.rollback();
            session.invalidate();
            e.printStackTrace();
        }


        if (user != null) {
            session.setAttribute( "utente", user );

            s.setUtente( user );

            sessioneRepository.save( s );

            if (partecipazioneRepository.findByUtenteIdAndRuoloIsLeader( user.getId() ) != null) {
                return "homePageLeader";
            } else {

                return "homePage_progetti";
            }
        }else {
            modelMap.put( "failed", "Login Failed" );
             return "redirect:login?error";
        }


        }
        else {
            return "redirect:profiloUtente";

        }
    }


    @GetMapping("/logout")
    public String logout(ModelMap modelMap, HttpSession session){
    session.removeAttribute( "utente" );
    return "index";
    }





}
