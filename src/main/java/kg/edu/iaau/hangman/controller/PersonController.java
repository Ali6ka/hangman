package kg.edu.iaau.hangman.controller;

import kg.edu.iaau.hangman.entity.Person;
import kg.edu.iaau.hangman.service.PersonService;
import kg.edu.iaau.hangman.service.SecretWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class PersonController
{
    @Autowired
    private PersonService personService;

    @Autowired
    private SecretWordService secretWordService;

    @RequestMapping(method = RequestMethod.GET)
    public String getPersons(Model model, Principal principal)
    {
        if(!personService.isAdmin(principal.getName()))
        {
            return "accessDenied";
        }

        List<Person> personList = personService.findAll();
        model.addAttribute("users", personList);

        return "admin/user-list";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String addPerson(Model model, Principal principal)
    {
        if(!personService.isAdmin(principal.getName()))
        {
            return "accessDenied";
        }

        model.addAttribute("user", new Person());
        model.addAttribute("isNew", "true");

        return "admin/user-form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String savePerson(Principal principal, RedirectAttributes redAttrs,
                             @Valid @ModelAttribute("user") Person person,
                             BindingResult result, Model model)
    {
        if(!personService.isAdmin(principal.getName()))
        {
            return "accessDenied";
        }

        if(result.hasErrors()) {
            redAttrs.addFlashAttribute("result", "fail");
            return "redirect:/admin/users";
        }

        personService.save(person);
        redAttrs.addFlashAttribute("result", "success");
        return "redirect:/admin/users";
    }

    @RequestMapping(
            value = {"/update/{id}"},
            method = {RequestMethod.GET})
    public String updatePerson(Model model, Principal principal,
                               @PathVariable("id") int id)
    {
        if(!personService.isAdmin(principal.getName()))
        {
            return "accessDenied";
        }

        Person person = personService.findById(id);
        model.addAttribute("isNew", "false");
        model.addAttribute("user", person);

        return "admin/user-form";
    }

    @RequestMapping(
            value = {"/delete/{id}"},
            method = {RequestMethod.GET})
    public String userDelete(Model model, @PathVariable("id") int id,
                             RedirectAttributes redAttrs, Principal principal)
    {
        if(!personService.isAdmin(principal.getName()))
        {
            return "accessDenied";
        }

        try {
            personService.delete(personService.findById(id));
        } catch (Exception ex) {
            redAttrs.addFlashAttribute("result", "fail");
            return "redirect:/admin/users";
        }

        redAttrs.addFlashAttribute("result", "success");
        return "redirect:/admin/users";
    }
}
