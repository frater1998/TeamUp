package it.fgm.teamup.controllers;


import it.fgm.teamup.model.*;
import it.fgm.teamup.repository.*;
import it.fgm.teamup.services.IAttivitàService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class GestoreProgettoController {


    @Autowired
    IAttivitàService attivitàService;


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

    int progetto_id;

    @GetMapping("/cercaProgetti")
    public String getcercaProgetti(@ModelAttribute Progetto progetto, BindingResult result, Model model) {

        model.addAttribute( "progetto", progetto );
        return "cercaProgetti";
    }

    @GetMapping("/listaMusica")
    public String cercaProgettiperMusica(
            @ModelAttribute Progetto progetto,

            Model model) {
        String musica = "musica";

        model.addAttribute( "progetto",
                progettoRepository.findAllByCategoriaIsMusica( musica ) );
        return "listaMusica";
    }

   @GetMapping("/listaSport")
    public String cercaProgettiperSport(
            @ModelAttribute Progetto progetto,
            BindingResult result,
            Model model) {
        String sport = "sport";

        model.addAttribute( "progetto",
                progettoRepository.findAllByCategoriaIsSport( sport ) );
        return "listaSport";
    }



    @GetMapping("/listaCinema")
    public String cercaProgettiperCinema(
            @ModelAttribute Progetto progetto,
            BindingResult result,
            Model model) {
        String cinema = "cinema";

        model.addAttribute( "progetto",
                progettoRepository.findAllByCategoriaIsCinema( cinema ) );
        return "listaCinema";
    }
    @GetMapping("/listaDidattica")
    public String cercaProgettiperDidattica(
            @ModelAttribute Progetto progetto,
            BindingResult result,
            Model model) {
        String didattica = "didattica";

        model.addAttribute( "progetto",
                progettoRepository.findAllByCategoriaIsDidattica( didattica ) );
        return "listaDidattica";
    }

    @GetMapping("/listaAltro")
    public String cercaProgettiperAltro(
            @ModelAttribute Progetto progetto,
            BindingResult result,
            Model model) {
        String altro = "altro";

        model.addAttribute( "progetto",
                progettoRepository.findAllByCategoriaIsAltro( altro ) );
        return "listaAltro";
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
                return "homePageLeader";
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
            HttpSession session,
            ModelMap modelMap,
            Sessione sessione) {
        sessione.setId( session.getId() );
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            Sessione s = sessioneRepository.findById( sessione.getId() );
          Progetto  progetto = progettoRepository.findById( progetto_id );
          System.out.println(progetto_id);
            Utente utente = s.getUtente();
            Partecipazione partecipazione = partecipazioneRepository.findByUtenteId( utente.getId() );

           try {
                session.getAttribute( "attivita" );
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (attivita != null) {
                session.setAttribute( "attività", attivita );


                System.out.println( attivita.getId() );
                attivita.setProgetto( progettoRepository.findById( progetto_id ) );
                attivitàRepository.save( attivita );
                System.out.println( attivita.getObiettivi() );
            } else {
                modelMap.put( "failed", "Add Project Failed" );
                return "redirect:/progetto?error";
            }
        }

       modelMap.addAttribute( "progetto", progettoRepository.findById( progetto_id ) );
        return "singoloprog";
    }


    @GetMapping("/nuovaAttività")
    public String getNuovaAttività(@ModelAttribute Attivita attivita, Model model, HttpSession session
    ) {

        if (session.getAttribute( "attività" ) == null) {
            model.addAttribute( "attività", attivita );
            return "nuovaAttività";
        } else {
            return "redirect:/nuovaAttività";
        }
    }

    @GetMapping("/modificaAttività/{id}")
    public ModelAndView updateAttivita(@PathVariable("id") int id,
                                 Model model
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject( "attivita", attivitàRepository.findById( id ) );
        mav.setViewName( "modificaAttività" );
        return mav;
        //model.addAttribute( "attivita", attivita );

    }

    @PostMapping("/modificaAttività/{id}")
    public String postupdateAttivita(@PathVariable("id") int id,
                                     Attivita attivita,
                                     BindingResult result, Model model,
                                     HttpSession session, Sessione sessione) {

        if (result.hasErrors()) {
            return "redirect:/modificaAttività";
        }

        sessione.setId( session.getId() );
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            Sessione s = sessioneRepository.findById( sessione.getId() );
            Progetto progetto = progettoRepository.findById( progetto_id );
            System.out.println( progetto_id );
            Utente utente = s.getUtente();
            Partecipazione partecipazione = partecipazioneRepository.findByUtenteId( utente.getId() );

            //attivita = attivitàRepository.findById( id );
            String a = attivita.getPercentualeCompletamento();
            System.out.println( a );
            System.out.println( id );
            attivitàService.save( a, id );
            //  attivitàRepository.save( attivita );

            model.addAttribute( "progetto", progettoRepository.findById( progetto_id ) );


            model.addAttribute( "attivita", attivita );

            if (partecipazioneRepository.findByUtenteIdAndRuoloIsLeader( utente.getId() ) != null) {

                return "homePageLeader";
            }
        }

               return "homePage_progetti";



    }






    @GetMapping("singoloprog/{id}")
    public String getSingoloprog(@PathVariable("id") int id, ModelMap model,
                                 ModelAndView modelAndView,
                                 @ModelAttribute Progetto progetto,
                                 HttpServletRequest request,
                                 HttpSession session, Sessione sessione) {

        sessione.setId( session.getId() );
        if (sessioneRepository.findById( sessione.getId() ) != null) {
            Sessione s = sessioneRepository.findById( sessione.getId() );

            System.out.println( id );
            Utente utente = s.getUtente();
            Partecipazione partecipazione = partecipazioneRepository.findByUtenteId( utente.getId() );

            if (partecipazioneRepository.findByUtenteIdAndRuoloIsLeader( utente.getId() ) != null) {

                model.addAttribute( "progetto", progettoRepository.findById( id ) );

                model.addAttribute( "attivita", attivitàRepository.findAllByProgettoId(id) );

                progetto_id = id;

                return "singoloprog";
            }
        }



        model.addAttribute( "progetto", progettoRepository.findById( id ) );

        model.addAttribute( "attivita", attivitàRepository.findAllByProgettoId(id) );

       progetto_id = id;


        return "singoloprognomod";
    }







   /* @PostMapping("singoloprog/{id}")
    public String postSingoloprog(@PathVariable("id") int id,
                                  ModelMap modelMap,
                                  @ModelAttribute Progetto progetto,
                                  HttpSession session) {


        progetto = progettoRepository.findById( id );

        progetto.setDescrizione( progetto.getDescrizione() );
        progetto.setTitolo( progetto.getTitolo() );

        return "singoloprog/{id}";

    }*/


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

   /* @GetMapping("/listaProgetti")
    public String listaProgetti(@ModelAttribute Progetto progetto){


    }*/


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
        String p = progetto.getDescrizione();
        System.out.println(p);
        System.out.println(progetto_id);
        utenteService.saveProg( p, progetto_id );

        model.addAttribute( "progetto", progetto );

        return "modificaCompletata";
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
        Utente utente = utenteRepository.findById( id );
        utenteRepository.delete( utente );
        model.addAttribute( "utente", partecipazioneRepository.findAll() );
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

    @GetMapping("/profilonomod/{id}")
    public String getProfiloNomod(@PathVariable("id") int id, HttpSession session, Model model) {
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
        return "profilonomod";
    }

    @GetMapping("/updateProfilo/{id}")
    public ModelAndView showUpdateFormUser(@PathVariable("id") int id, Model model, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.addObject( "utente", utenteRepository.findById( id ) );
        mav.setViewName( "updateProfilo" );
        return mav;
    }

    @PostMapping("/updateProfilo/{id}")
    public ModelAndView updateUtente(@PathVariable("id") int id,
                              @Validated Utente utente,
                               BindingResult result,
                               ModelAndView model,
                               HttpSession session) {

        utenteService.salva( utente.getDescrizione(),id );
        return new ModelAndView("redirect:/profilo");
    }

/*
   @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String visualizzaEntità(
            Model model,
            HttpSession session)
    {

       List<Progetto> progetto = progettoRepository.findAll();

        model.addAttribute( "progetto",progetto );
        model.addAttribute( "utente", utenteRepository.findAll() );



         //   model.addAttribute( "progetto", progettoRepository.findAll() );
         // model.addAttribute( "utente" , utenteRepository.findAll());
        return "admin";
    }
*/
@GetMapping("/admin")
public String progetti(Model model,
                       @ModelAttribute Progetto progetto)
{
    model.addAttribute("prog", progettoRepository.findAll() );

    return "admin";

}
    @GetMapping("/visualizzap")
    public String visualizzaProgetti(
            @ModelAttribute Progetto progetto,
            Model model) {
        model.addAttribute( "progetto", progettoRepository.findAll() );
        return "visualizzap";
    }

    @GetMapping("/visualizzau")
    public String visualizzaU(
            @ModelAttribute Utente utente,
            @ModelAttribute Progetto progetto,
            Model model) {
        model.addAttribute( "utente", utenteRepository.findAll() );
        model.addAttribute( "progetto", progettoRepository.findAll() );
        return "visualizzau";
    }

    @GetMapping("/deleteU/{id}")
    public String deleteU(@PathVariable("id") int id, Model model) {
        Utente utente = utenteRepository.findById( id );
        utenteRepository.delete( utente );
        model.addAttribute( "utente", partecipazioneRepository.findAll() );
        return "redirect:/visualizzau";
    }
    @GetMapping("/deletep/{id}")
    public String deleteP(@PathVariable("id") int id, Model model) {
        Progetto progetto = progettoRepository.findById( id );
        progettoRepository.delete( progetto );
        model.addAttribute( "progetto", progettoRepository.findAll() );
        return "redirect:/visualizzau";
    }

    @GetMapping("/deletepart/{id}")
    public String deletePart(@PathVariable("id") int id, Model model) {
        Partecipazione partecipazione = partecipazioneRepository.findById( id );
        partecipazioneRepository.delete( partecipazione );
        model.addAttribute( "partecipazione", partecipazioneRepository.findAll() );
        return "redirect:/visualizzaPartecipanti";
    }

}






