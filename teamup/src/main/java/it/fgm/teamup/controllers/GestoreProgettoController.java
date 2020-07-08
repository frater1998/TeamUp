package it.fgm.teamup.controllers;


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
import org.springframework.validation.annotation.Validated;
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
                progettoRepository.findAllByCategoriaIsMusica( musica ) );
        return "listaMusica";
    }

    @GetMapping("/homePage_progetti")
    public String homePage_progetti(@ModelAttribute Progetto progetto, BindingResult result, Model model) {

        return "homePage_progetti";
    }

    @GetMapping("/progetto")
    public String creaProgetto(@ModelAttribute Progetto progetto,
                               ModelMap modelMap,
                               HttpSession session) {

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
                               Session ses) {

        Sessione sessione = new Sessione();
        sessione.setId( session.getId() );

        if (sessioneRepository.findById( sessione.getId() ) != null) {
            Sessione s = sessioneRepository.findById( sessione.getId() );
            Utente utente = s.getUtente();
            System.out.println( utente.getId() );
            try {
                session.getAttribute( "utente" );
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (progetto != null) {
                session.setAttribute( "progetto", progetto );
                progettoRepository.save( progetto );
                System.out.println( progetto.getTitolo() );
                Partecipazione partecipazione = new Partecipazione();
                partecipazione.setRuolo( "LEADER" );
                partecipazione.setPartecipazione_confermata( true );
                partecipazione.setUtente( utente );
                partecipazione.setProgetto( progetto );
                partecipazioneRepository.save( partecipazione );
                return "homePage_progetti";
            } else {
                modelMap.put( "failed", "Add Project Failed" );
                return "redirect:progetto?error";
            }


        } else {
            return "redirect:homePage_progetti";

        }
    }


    @PostMapping("/nuovaAttività")
    public String newAttivita(
            @ModelAttribute Attivita attivita,
            Progetto progetto,
            HttpSession session,
            ModelMap modelMap,
            Sessione sessione) {

        sessione.setId( session.getId() );
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            Sessione s = sessioneRepository.findById( sessione.getId() );
            Utente utente = s.getUtente();
            Partecipazione partecipazione = partecipazioneRepository.findByUtenteId( utente.getId() );
            progetto = progettoRepository.findByTitolo( progetto.getTitolo() );


            try {
                session.getAttribute( "attivita" );
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (attivita != null) {
                session.setAttribute( "attività", attivita );

                attivita.setObiettivi( attivita.getObiettivi() );
                attivita.setPercentualeCompletamento( attivita.getPercentualeCompletamento() );
                System.out.println( attivita.getId() );
                attivitàRepository.save( attivita );
                System.out.println( attivita.getObiettivi() );
                return "singoloprog";
            } else {
                modelMap.put( "failed", "Add Project Failed" );
                return "redirect:/progetto?error";
            }
        }
        return "singoloprog";
    }


    @GetMapping("/nuovaAttività")
    public String getNuovaAttività(@ModelAttribute Attivita attivita, Model model, HttpSession session
    ) {

        if (session.getAttribute( "attività" ) == null) {
            model.addAttribute( "attività", attivita );
            return "nuovaAttività";
        } else {
            return "nuovaAttività:?error";
        }
    }

    @GetMapping("/modificaAttività/{id}")
    public String updateAttivita(@PathVariable("id") int id,
                                 Model model
    ) {
        Attivita attivita = attivitàRepository.findById( id );
        model.addAttribute( "attività", attivita );
        return "modificaAttività";
    }

    @PostMapping("/modificaAttività/{id}")
    public String postupdateAttivita(@PathVariable("id") int id,
                                     Attivita attivita,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            attivita.setId( id );
            return "modificaAttivitò";
        }

        attivitàRepository.save( attivita );

        return "redirect:/singoloprog";
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
    public String postSingoloprog(@PathVariable("id") int id,
                                  ModelMap modelMap,
                                  @ModelAttribute Progetto progetto,
                                  HttpSession session) {

        progetto = progettoRepository.findById( id );

        progetto.setDescrizione( progetto.getDescrizione() );
        progetto.setTitolo( progetto.getTitolo() );

        return "singoloprog/{id}";

    }


    @GetMapping("creapart-tm/{id}")
    public String creapartTm(
            @PathVariable("id") int id,
            HttpSession session
    ) {

        Sessione sessione = new Sessione();
        sessione.setId( session.getId() );
        System.out.println( session.getId() );
        System.out.println( sessione.getId() );
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            Sessione s = sessioneRepository.findById( sessione.getId() );
            Utente utente = s.getUtente();
            Progetto progetto = progettoRepository.findById( id );
            Partecipazione partecipazione = new Partecipazione();
            partecipazione.setPartecipazione_confermata( false );
            partecipazione.setRuolo( "TEAM-MATE" );
            partecipazione.setProgetto( progetto );
            partecipazione.setUtente( utente );
            System.out.println( partecipazione.getUtente().getNome() );
            System.out.println( partecipazione.getProgetto().getTitolo() );
            System.out.println( partecipazione.getPartecipazione_confermata() );
            System.out.println( partecipazione.getRuolo() );

            partecipazioneRepository.save( partecipazione );

        }

        return "creapart-tm";
    }
          /*  if (session.getAttribute( "progetto" ) == null) {
               // model.put( "progetto", new Progetto() );
              //  return "singoloprog";
            } else {
                return "redirect:singoloprog:?error";
            }
*/

    @GetMapping("/listaRichieste")
    public String listaRichieste(@ModelAttribute Partecipazione partecipazione,
                                 Model model) {

        model.addAttribute( "partecipazione", partecipazioneRepository.
                findByPartecipazione_confermataIsFalse( false ) );

        return "listaRichieste";
    }

    @PostMapping("/confermaRichiesta/{id}")
    public String confermaRichiesta(@PathVariable("id") long id,
                                    Model model,
                                    HttpSession session) {


        Partecipazione partecipazione = partecipazioneRepository.findById( id );
        partecipazione.setPartecipazione_confermata( true );
        model.addAttribute( "partecipazione", partecipazione );
        partecipazioneRepository.save( partecipazione );

        return "confermaRichiesta";
    }


    @GetMapping("/confermaRichiesta/{id}")
    public String confermaRichiesta(@PathVariable("id") long id,
                                    Model model
    ) {
        Partecipazione partecipazione = partecipazioneRepository.findById( id );

        partecipazione.setPartecipazione_confermata( true );

        model.addAttribute( "partecipazione", partecipazione );

        partecipazioneRepository.save( partecipazione );


        return "confermaRichiesta";
    }

    @GetMapping("deleteRichiesta/{id}")
    public String deleteRichiesta(@PathVariable("id") long id,
                                  Model model) {
        Partecipazione partecipazione = partecipazioneRepository.findById( id );
        partecipazioneRepository.delete( partecipazione );
        return "listaRichieste";
    }


    @GetMapping("/update-prog/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Progetto progetto = progettoRepository.findById( id );
        model.addAttribute( "progetto", progetto );
        return "update-prog";
    }


    @GetMapping("/visualizzaprogtm")
    public String listprogtm(@ModelAttribute Partecipazione partecipazione,
                             Model model,
                             HttpSession session) {
        Sessione sessione = new Sessione();
        sessione.setId( session.getId() );
        System.out.println( session.getId() );
        System.out.println( sessione.getId() );
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            System.out.println( "ciao1" );
            Sessione s = sessioneRepository.findById( sessione.getId() );
            Utente utente = s.getUtente();
            System.out.println( "ciao2" );
            partecipazione.setUtente( utente );
            System.out.println( "ciao3" );
            System.out.println( partecipazione.getUtente().getNome() );
            if (partecipazione.getUtente() == utente) {
                partecipazioneRepository.findByPartecipazione_confermataIsTrue( partecipazione.getUtente() );
                model.addAttribute( "partecipazione",
                        partecipazioneRepository.findByPartecipazione_confermataIsTrue( partecipazione.getUtente() ) );
            }
        }
        return "visualizzaprogtm";
    }


    @GetMapping("/lista")
    public String getListLeader(@ModelAttribute Partecipazione partecipazione,
                                HttpSession session,
                                Model model) {
        Sessione sessione = new Sessione();
        sessione.setId( session.getId() );
        System.out.println( session.getId() );
        System.out.println( sessione.getId() );
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            System.out.println( "ciao1" );
            Sessione s = sessioneRepository.findById( sessione.getId() );
            Utente utente = s.getUtente();
            System.out.println( utente.getNome() );
            System.out.println( "ciao2" );
            partecipazione.setUtente( utente );
            int p = partecipazione.getUtente().getId();
            if (p == utente.getId()) {
                partecipazioneRepository.
                        findByPartecipazione_confermataIsFalseAndRuoloIsLeader();
                model.addAttribute( "partecipazione", partecipazioneRepository.
                        findByPartecipazione_confermataIsFalseAndRuoloIsLeader() );
            }
            System.out.println( "ciao3" );
            System.out.println( partecipazione.getUtente().getNome() );
        }
        return "lista";
    }

    @PostMapping("/update-prog/{id}")
    public String updateProgetto(@PathVariable("id") int id,
                                 Progetto progetto,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            progetto.setId( id );
            return "update-prog";
        }

        progettoRepository.save( progetto );

        return "redirect:/visualizzap";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
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


    @GetMapping("/profilo")
    public String showSuccess(ModelMap modelMap,
                              HttpSession session) {

        Sessione sessione = new Sessione();
        sessione.setId( session.getId() );
        System.out.println( session.getId() );
        System.out.println( sessione.getId() );
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            System.out.println( "ciao1" );
            Sessione s = sessioneRepository.findById( sessione.getId() );
            Utente utente = s.getUtente();
            System.out.println( utente.getNome() );
            System.out.println( "ciao2" );

            modelMap.addAttribute( "utente", utente );
        }

        return "profilo";

    }


    @GetMapping("/listaPartecipanti/{id}")
    public String listaPartecipanti(@PathVariable("id") int id,
                                    @ModelAttribute Partecipazione partecipazione,
                                    HttpSession session,
                                    Model model) {
        model.addAttribute( "partecipazione",
                partecipazioneRepository.findByProgettoId( id ) );

        return "listaPartecipanti";
    }


    @GetMapping("/visualizzaPartecipanti")
    public String visualizzapart(
            @ModelAttribute Partecipazione partecipazione,
            HttpSession session,
            Model model) {

        Sessione sessione = new Sessione();
        sessione.setId( session.getId() );
        System.out.println( session.getId() );
        System.out.println( sessione.getId() );
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            System.out.println( "ciao1" );
            Sessione s = sessioneRepository.findById( sessione.getId() );
            Utente utente = s.getUtente();
            System.out.println( utente.getNome() );
            System.out.println( "ciao2" );
            partecipazione.setUtente( utente );
            int p = partecipazione.getUtente().getId();
            if (p == utente.getId()) {


                model.addAttribute( "partecipazione",
                        partecipazioneRepository.findAllByPartecipazione_confermataIsTrueAndAndRuolo() );
            }

        }
        return "visualizzaPartecipanti";
    }


    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        Partecipazione partecipazione = partecipazioneRepository.findById( id );
        partecipazioneRepository.delete( partecipazione );
        model.addAttribute( "partecipazione", partecipazioneRepository.findAll() );
        return "redirect:/visualizzaPartecipanti";
    }

    @GetMapping("/profilo/{id}")
    public String getProfiloU(@PathVariable("id") int id, HttpSession session, Model model) {
        Sessione sessione = new Sessione();
        sessione.setId( session.getId() );
        System.out.println( session.getId() );
        System.out.println( sessione.getId() );
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            System.out.println( "ciao1" );
            Sessione s = sessioneRepository.findById( sessione.getId() );

            Utente u = utenteRepository.findById( id );
            model.addAttribute( "utente", u );
        }
        return "profilo";
    }

    @GetMapping("/modProf/{id}")
    public ModelAndView showUpdateFormUser(@PathVariable("id") int id, Model model, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.addObject( "utente", utenteRepository.findById( id ) );
        mav.setViewName( "modprof" );
        return mav;
    }

    @PostMapping("/modProf/{id}")
    public ModelAndView updateUtente(@PathVariable("id") int id,
                              @Validated Utente utente,
                               BindingResult result,
                               ModelAndView model,
                               HttpSession session) {

        utenteService.salva( utente );
        return new ModelAndView("redirect:/profilo");
    }
}






