package it.fgm.teamup.controllers;


import com.sun.xml.bind.v2.schemagen.xmlschema.LocalAttribute;
import it.fgm.teamup.model.*;
import it.fgm.teamup.repository.*;
import it.fgm.teamup.services.IProgettoService;
import it.fgm.teamup.services.IUtenteService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Controller
public class GestoreProgettoController {
    @Autowired
    IUtenteRepository utenteRepository;
    @Autowired
    IAttivitàRepository attivitàRepository;
    @Autowired
    IProgettoRepository progettoRepository;
    @Autowired
    PartecipazioneRepository partecipazioneRepository;
    @Autowired
    IUtenteService utenteService;
    @Autowired
    ISessioneRepository sessioneRepository;
    IProgettoService progettoService;

    @GetMapping("/cercaProgetti")
    public String getcercaProgetti(@ModelAttribute Progetto progetto, BindingResult result, Model model) {

        model.addAttribute( "progetto", progetto );
        return "cercaProgetti";
    }

    @GetMapping("/listaMusica")
    public String cercaProgettiperMusica(
                                        @ModelAttribute Progetto progetto,
                                        BindingResult result,
                                        Model model) {
         String musica = "musica";

        model.addAttribute( "progetto",
                progettoRepository.findAllByCategoriaIsMusica(musica) );
        return "listaMusica";
    }

    @GetMapping("/homePage_progetti")
    public String homePage_progetti(@ModelAttribute Progetto progetto, BindingResult result, Model model) {

        return "homePage_progetti";
    }

    @GetMapping("/progetto")
    public String creaProgetto(@ModelAttribute Progetto progetto,
                                ModelMap modelMap,
                                HttpSession session){

        if (session.getAttribute( "progetto" ) == null) {
            modelMap.put( "progetto", new Progetto() );
            return "progetto";
        } else {
            return "progetto:?error";
        }

    }

    @PostMapping("/progetto")
    public String postcreaProg(@ModelAttribute Progetto progetto,
                               ModelMap modelMap, HttpSession session,
                               Session ses){

        Sessione sessione = new Sessione();
        sessione.setId(session.getId());

        if( sessioneRepository.findById(sessione.getId())!= null){
           Sessione s =  sessioneRepository.findById( sessione.getId() );
             Utente utente =  s.getUtente();
            System.out.println(utente.getId());
            try {
               session.getAttribute( "utente");
            } catch (Exception e){
                e.printStackTrace();
            }

            if (progetto != null){
                session.setAttribute( "progetto", progetto );
                progettoRepository.save( progetto );
                System.out.println(progetto.getTitolo());
                Partecipazione partecipazione = new Partecipazione();
                partecipazione.setRuolo( "LEADER" );
                partecipazione.setPartecipazione_confermata( true );
                partecipazione.setUtente( utente);
                partecipazione.setProgetto( progetto );
                partecipazioneRepository.save( partecipazione );
                return "homePage_progetti";
            } else {
                modelMap.put( "failed", "Add Project Failed" );
                return "redirect:progetto?error";
            }


        }
        else {
            return "redirect:homePage_progetti";

        }
    }


    @PostMapping("/nuovaAttività")
    public String newAttivita(@ModelAttribute Progetto progetto,
                                      Utente u,
                                      Attivita attivita,
                                      Partecipazione partecipazione,
                                      HttpSession session,
                              ModelMap modelMap,
                              Sessione sessione) {

        sessione.setId( session.getId() );
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            Sessione s = sessioneRepository.findById( sessione.getId() );
            Utente utente = s.getUtente();
            partecipazione = partecipazioneRepository.findByUtenteId( utente.getId() );
            progetto = partecipazione.getProgetto();
            if (progettoService.findById( progetto.getId() )!= null)
            System.out.println( utente.getId() );
            try {
                session.getAttribute( "attivita" );
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (attivita != null) {
                session.setAttribute( "attività", attivita );
                attivita.setProgetto( progetto );
                attivita.setObiettivi( attivita.getObiettivi() );
                attivita.setPercentualeCompletamento( attivita.getPercentualeCompletamento() );
                System.out.println(attivita.getId());
                attivitàRepository.save( attivita );
                System.out.println( attivita.getObiettivi() );
                return "homePage_progetti";
            } else {
                modelMap.put( "failed", "Add Project Failed" );
                return "redirect:progetto?error";
            }
        }
        return "profiloUtente";
    }



@GetMapping("/nuovaAttività")

public String getNuovaAttività(ModelMap modelMap, HttpSession session,
                               @ModelAttribute  Attivita attivita){


    if (session.getAttribute( "attività" ) == null) {
        modelMap.put( "attività", new Attivita() );
        return "nuovaAttività";
    } else {
        return "nuovaAttività:?error";
    }


}

    @GetMapping("/visualizzap")
    public String visualizzaProgetti(
             @ModelAttribute Progetto progetto,
             Model model) {

        model.addAttribute( "progetto", progettoRepository.findAll() );

        return "visualizzap";
    }

    @GetMapping("singoloprog/{id}")
    public String getSingoloprog(@PathVariable("id") int id, ModelMap model,
                                 ModelAndView modelAndView,
                                 @ModelAttribute Progetto progetto,
                                 HttpServletRequest request,
                                 HttpSession session) {

        model.addAttribute( "progetto", progettoRepository.findById( id ) );


        return "singoloprog";
    }

    @PostMapping("singoloprog/{id}")
    public String postSingoloprog(@PathVariable("id" ) int id,
                                  ModelMap modelMap,
                                  @ModelAttribute Progetto progetto,
                                  HttpSession session)
    {

        progetto = progettoRepository.findById( id );

        progetto.setDescrizione( progetto.getDescrizione() );
        progetto.setTitolo( progetto.getTitolo() );

        return "singoloprog/{id}";

    }


    @GetMapping("creapart-tm/{id}")
    public String creapartTm(
            @PathVariable("id") int id,
            HttpSession session
    ){

        Sessione sessione = new Sessione();
        sessione.setId(session.getId());
        System.out.println(session.getId());
        System.out.println(sessione.getId());
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            Sessione s = sessioneRepository.findById( sessione.getId() );
            Utente utente = s.getUtente();
            Progetto progetto = progettoRepository.findById( id );
            Partecipazione partecipazione = new Partecipazione();
            partecipazione.setPartecipazione_confermata( false );
            partecipazione.setRuolo( "TEAM-MATE" );
            partecipazione.setProgetto( progetto);
            partecipazione.setUtente( utente );
            System.out.println(partecipazione.getUtente().getNome());
            System.out.println(partecipazione.getProgetto().getTitolo());
            System.out.println(partecipazione.getPartecipazione_confermata());
            System.out.println(partecipazione.getRuolo());

            partecipazioneRepository.save( partecipazione );

        }

        return "creapart-tm" ;
    }
          /*  if (session.getAttribute( "progetto" ) == null) {
               // model.put( "progetto", new Progetto() );
              //  return "singoloprog";
            } else {
                return "redirect:singoloprog:?error";
            }
*/

    @GetMapping("/listaRichieste")
    public String listaRichieste ( @ModelAttribute Partecipazione partecipazione,
    Model model) {

        model.addAttribute( "partecipazione", partecipazioneRepository.
                findByPartecipazione_confermataIsFalse(false) );

        return "listaRichieste";
    }

    @PostMapping("/confermaRichiesta/{id}")
    public String confermaRichiesta(@PathVariable("id") long id,
                                    Model model,
                                    HttpSession session){


        Partecipazione partecipazione = partecipazioneRepository.findById( id );
        partecipazione.setPartecipazione_confermata( true );
        model.addAttribute( "partecipazione", partecipazione );
        partecipazioneRepository.save( partecipazione );

        return "confermaRichiesta" ;
    }


    @GetMapping("/confermaRichiesta/{id}")
    public String confermaRichiesta(@PathVariable("id") long id,
                                    Model model
                                    ){
        Partecipazione partecipazione = partecipazioneRepository.findById( id );

        partecipazione.setPartecipazione_confermata( true );

        model.addAttribute( "partecipazione", partecipazione );

        partecipazioneRepository.save( partecipazione );


        return "confermaRichiesta" ;
    }

    @GetMapping("/update-prog/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Progetto progetto = progettoRepository.findById(id);
        model.addAttribute("progetto", progetto);
        return "update-prog";
    }

    @PostMapping("/update-prog/{id}")
    public String updateProgetto(@PathVariable("id") int id,
                                 Progetto progetto,
                             BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            progetto.setId( id );
            return "update-prog";
        }

        progettoRepository.save( progetto );

        return "redirect:/visualizzap";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        Progetto progetto = progettoRepository.findById( id );
        progettoRepository.delete( progetto );
        model.addAttribute( "progetto", progettoRepository.findAll() );
        return "visualizzap";
    }

    @GetMapping("/ListaProgetti")
    public String ListProg(
            HttpServletRequest request, HttpServletResponse response, @ModelAttribute Progetto progetto, Model model) {

        model.addAttribute( "progetto", progettoRepository.findAll() );

        return "ListaProgetti";
    }

    /*
    @GetMapping("/singoloprog")
    public String SingleProg(
            HttpServletRequest request, HttpServletResponse response, @ModelAttribute Progetto progetto, Model model) {

        model.addAttribute( "progetto", progetto );

        return "singoloprog";
    }
*/



    public void creaPartecipazioneL( Partecipazione partecipazione){

        partecipazione.setRuolo( "LEADER");
        partecipazione.setPartecipazione_confermata( true );

        partecipazioneRepository.save( partecipazione );


    }

}



