package it.fgm.teamup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfiloController {

    @GetMapping("/profilo")
    public String Profilo(){

        return "profilo";
    }
}