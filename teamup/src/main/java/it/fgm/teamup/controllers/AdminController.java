package it.fgm.teamup.controllers;


import it.fgm.teamup.model.Admin;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String doGet(@ModelAttribute Admin admin){

        return "admin";
    }

}