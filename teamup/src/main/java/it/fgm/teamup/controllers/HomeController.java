package it.fgm.teamup.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session){

        @SuppressWarnings( "unchecked" )
        List<String> messages = (List<String>) session.getAttribute( "MY_SESSION" );

        if (messages == null){

            messages = new ArrayList<>();
        }

        model.addAttribute( "sessionMessages", messages );

        return "index";
    }

    @PostMapping("/persistMessage")
    public String persist(@RequestParam("msg") String msg, HttpServletRequest request){

        @SuppressWarnings( "unchecked" )
                List<String> msgs = (List<String>) request.getSession().getAttribute( "MY_ SESSION" );

        if (msgs == null){
            msgs = new ArrayList<>();
            request.getSession().setAttribute( "MY_SESSION" , msgs);

        }

        msgs.add( msg );

        request.getSession().setAttribute( "MY_SESSION", msg );

        return "redirect:/";
    }


    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request){
        request.getSession().invalidate();
        return "index";
    }


}
