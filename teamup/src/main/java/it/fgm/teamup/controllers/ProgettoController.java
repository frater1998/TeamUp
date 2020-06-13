package it.fgm.teamup.controllers;


import it.fgm.teamup.model.Progetto;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.IProgettoRepository;
import it.fgm.teamup.services.IProgettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProgettoController {


    IProgettoService progettoService;

    @Autowired
    IProgettoRepository progettoRepository;

    @PostMapping("/ricerca_progetti")
    public Progetto cercaProgetti(@ModelAttribute Progetto progetto, BindingResult result, Model model) {

        Progetto p = progettoService.findById( progetto.getId() );

        return p;
    }

    @GetMapping("/homePage_progetti")
    public String homePage_progetti(@ModelAttribute Progetto progetto, BindingResult result, Model model) {

        return "homePage_progetti";
    }

    @PostMapping("/progetto")
    public String creaProgetto(@ModelAttribute Progetto progetto, BindingResult result, Model model) {


        if (result.hasErrors()) {
            return "redirect:progetto";
        }

        progetto.setTitolo( progetto.getTitolo() );
        progetto.setCategoria( progetto.getCategoria() );
        progetto.setDescrizione( progetto.getDescrizione() );

        Progetto p = progettoRepository.save( progetto );

        return "index";
    }

    @GetMapping("/progetto")
    public String progetto(@ModelAttribute Progetto progetto) {

        return "progetto";
    }

    @GetMapping("/visualizzap")
    public String visualizzaProgetti(
            HttpServletRequest request, HttpServletResponse response, @ModelAttribute Progetto progetto, Model model) {

        model.addAttribute( "progetto", progettoRepository.findAll() );

        return "visualizzap";
    }

    @GetMapping("/update-prog/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Progetto progetto = progettoRepository.findById(id);
        model.addAttribute("progetto", progetto);
        return "update-prog";
    }

    @PostMapping("/update-prog/{id}")
    public String updateProgetto(@PathVariable("id") int id,  Progetto progetto,
                             BindingResult result, Model model) {
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
}




