package it.fgm.teamup.controllers;
import it.fgm.teamup.model.Partecipazione;
import it.fgm.teamup.model.Progetto;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.IProgettoRepository;
import it.fgm.teamup.repository.IUtenteRepository;
import it.fgm.teamup.services.IPartecipazioneService;
import it.fgm.teamup.services.IUtenteService;
import org.apache.catalina.connector.Request;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

@Controller
public class UtenteController {



   @Autowired
    IUtenteService utenteService;

    @Autowired
    IUtenteRepository utenteRepository;

    @Autowired
    IProgettoRepository progettoRepository;



    IPartecipazioneService partecipazioneService;




    //Richiesta di tipo GET per la registrazione
    @GetMapping("/registrati")
    public String get(@ModelAttribute  Utente utente, Locale locale, ModelMap modelMap) {

        modelMap.put( "utente", new Utente() );

        return "registrati";
    }

    // Registrazione nuovo utente Controller
    @PostMapping("/registrati")
    public String post(@ModelAttribute("utente")  Utente utente,
                       BindingResult result, ModelMap modelMap,
                       HttpSession session)  {

        if(result.hasErrors()){
            return "registrati";
        }

        utente.setNome(utente.getNome());
        utente.setCognome(utente.getCognome());
        utente.setEmail(utente.getEmail());
        utente.setPassword(utente.getPassword());


        Utente u = utenteRepository.save(utente);
        session.setAttribute( "utente", utente );

        return "login";

    }

    private static final long serialVersionUID = 1L;





    @PostMapping("/modificaDatiUtente")
    public String updateUser(@ModelAttribute  Utente utente, BindingResult result, Model model){

        /* recupera utente da id */


        return "profiloUtente";

    }

/*
    @PostMapping("/progetto")
    public String creaProgetto(@ModelAttribute Progetto progetto, HttpServletRequest request,
                               HttpServletResponse response,
                               HttpSession session,
                               Utente utente) throws IOException, ServletException {

            progetto.setTitolo( progetto.getTitolo() );
            progetto.setCategoria( progetto.getCategoria() );
            progetto.setDescrizione( progetto.getDescrizione() );


            Session session1= null;

          
            System.out.println(session1);
            Partecipazione partecipazione = new Partecipazione();

            utenteService.creaPartecipazioneL( utente, progetto, partecipazione );

        /*Partecipazione p = new Partecipazione();
        p.creaPartecipazioneL(u.getUtente(), progetto);
        utenteService.aggPart( p, u.getPartecipazione() );



            progettoRepository.save( progetto );


        return "index";


    }

    @GetMapping("/progetto")
    public String progetto(@ModelAttribute Progetto progetto) {

        return "progetto";
    }
    */
    /*
    @GetMapping("/show-users")
    public String showAllUsers(HttpServletRequest request) {
        request.setAttribute("users", userService.showAllUsers());
        request.setAttribute("mode", "ALL_USERS");
        return "welcomepage";
    }
/*
    @RequestMapping("/delete-user")
    public String deleteUser(@RequestParam int id, HttpServletRequest request) {
        userService.deleteMyUser(id);
        request.setAttribute("users", userService.showAllUsers());
        return "welcomepage";
    }

    @RequestMapping("/edit-user")
    public String editUser(@RequestParam int id,HttpServletRequest request) {
        request.setAttribute("user", userService.editUser(id));
        return "welcomepage";
    }


 */

}
