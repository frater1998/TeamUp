package it.fgm.teamup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class modificaProfiloController {

    @GetMapping("/modificaProfilo")
    public String modificaProfilo() {

        return "modificaProfilo";
    }
}