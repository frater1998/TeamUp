package it.fgm.teamup.controllers;


import it.fgm.teamup.dao.UserDAO;

import it.fgm.teamup.exception.UtenteNonTrovato;
import it.fgm.teamup.model.Utente;
import it.fgm.teamup.repository.IUtenteRepository;
import it.fgm.teamup.services.IUtenteService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@Controller
public class LoginController {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    @Autowired
    IUtenteService utenteService;

    @GetMapping("/login")
    public String get(@ModelAttribute  Utente utente,HttpServletResponse response, HttpServletRequest request) {

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Utente user, HttpServletRequest request) {
        if(utenteService.findByEmailAndPassword(user.getEmail(), user.getPassword())!=null) {
            return "profiloUtente";
        }
        else {
               return "redirect:login?error";

        }
    }
}


