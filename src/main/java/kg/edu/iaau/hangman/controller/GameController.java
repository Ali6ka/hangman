package kg.edu.iaau.hangman.controller;

import kg.edu.iaau.hangman.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class GameController
{
    @Autowired
    private PersonService personService;

    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping(
            value = {"/logout"},
            method = {RequestMethod.GET}
    )
    public String logoutPage(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            (new SecurityContextLogoutHandler()).logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(
            value = {"/accessDenied"},
            method = {RequestMethod.GET}
    )
    public String accessDeniedPage(ModelMap model)
    {
        return "accessDenied";
    }

    @RequestMapping(
            value = {"/admin"},
            method = {RequestMethod.GET}
    )
    public String adminPage(ModelMap model, Principal principal)
    {
        if(!personService.isAdmin(principal.getName()))
        {
            return "accessDenied";
        }

        return "admin";
    }
}
