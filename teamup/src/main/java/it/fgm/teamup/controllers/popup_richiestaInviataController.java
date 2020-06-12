package it.fgm.teamup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class popup_richiestaInviataController {

    @GetMapping("/popup_richiestaInviata")
    public String popup_richiestaInviata(){

        return "popup_richiestaInviata";
    }
}