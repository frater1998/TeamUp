package it.fgm.teamup.controllers;



import it.fgm.teamup.model.Utente;
import it.fgm.teamup.services.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrazioneController {

    @Autowired
    IUtenteService userService;


    @GetMapping("/registrati")
    public String get(@ModelAttribute @Valid Utente utente, BindingResult result, Model model){
        return "registrati";
    }

    @PostMapping("/registrati")
    public String post(@ModelAttribute @Valid Utente utente, BindingResult result, Model model)  {

        if(result.hasErrors()){
            return "registrati";
        }


        //Utente u = userService.findById(utente.getId());
        utente.setEmail(utente.getEmail());

        model.addAttribute("utente", utente);

        //SALVATAGGIO MEX E UTENTE

        //Message mexInviato = new Message();
     //   mexInviato.setTitle(messageForm.getTitle());
       // mexInviato.setBody(messageForm.getBody());
        //mexInviato.setPhone(messageForm.getPhone());
        //exInviato.setUtente(utente);
       // tente.getMexInviati().add(mexInviato);
        userService.salva(utente);

        System.out.println(utente.getEmail());

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
