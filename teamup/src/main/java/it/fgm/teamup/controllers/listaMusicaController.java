package it.fgm.teamup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class listaMusicaController {

    @GetMapping("/listaMusica")
    public String listaMusica(){

        return "listaMusica";
    }
}
