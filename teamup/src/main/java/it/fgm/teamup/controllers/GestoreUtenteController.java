package it.fgm.teamup.controllers;
import it.fgm.teamup.model.Sessione;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.IProgettoRepository;
import it.fgm.teamup.repository.IUtenteRepository;
import it.fgm.teamup.services.IPartecipazioneService;
import it.fgm.teamup.services.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class GestoreUtenteController {



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
        utente.setDescrizione( utente.getDescrizione() );


        Utente u = utenteRepository.save(utente);

        session.setAttribute( "utente", utente );

        session.invalidate();

        return "index";

    }

    private static final long serialVersionUID = 1L;





    /*
    @RequestMapping("/delete-user")
    public String deleteUser(@RequestParam int id, HttpServletRequest request) {
        utenteRepository.delete(id);
        request.setAttribute("users", userService.showAllUsers());
        return "welcomepage";
    }
**/

}
