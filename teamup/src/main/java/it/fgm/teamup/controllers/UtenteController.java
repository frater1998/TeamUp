package it.fgm.teamup.controllers;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.services.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UtenteController {


    @Autowired
    IUtenteService userService;


    //Richiesta di tipo GET per la registrazione
    @GetMapping("/registrati")
    public String get(@ModelAttribute  Utente utente) {
        return "registrati";
    }

    // Registrazione nuovo utente Controller
    @PostMapping("/registrati")
    public String post(@ModelAttribute  Utente utente, BindingResult result)  {

        if(result.hasErrors()){
            return "registrati";
        }

        utente.setNome(utente.getNome());
        utente.setCognome(utente.getCognome());
        utente.setEmail(utente.getEmail());
        utente.setPassword(utente.getPassword());


        Utente u = userService.salva(utente);

        return "login";

    }

    private static final long serialVersionUID = 1L;


    @GetMapping("/login")
    public String get(@ModelAttribute  Utente utente, HttpServletResponse response, HttpServletRequest request) {

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Utente user, HttpServletRequest request) {
        if(userService.findByEmailAndPassword(user.getEmail(), user.getPassword())!=null) {
            return "homePage_progetti";
        }
        else {
            return "redirect:login?error";

        }
    }

    @PostMapping("/modificaDatiUtente")
    public String updateUser(@ModelAttribute  Utente utente, BindingResult result, Model model){

        /* recupera utente da id */


        return "profiloUtente";

    }
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
