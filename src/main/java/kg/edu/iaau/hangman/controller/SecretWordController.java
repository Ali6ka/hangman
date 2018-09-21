package kg.edu.iaau.hangman.controller;

import kg.edu.iaau.hangman.entity.SecretWord;
import kg.edu.iaau.hangman.service.PersonService;
import kg.edu.iaau.hangman.service.SecretWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/admin/words")
public class SecretWordController
{
    @Autowired
    private PersonService personService;

    @Autowired
    private SecretWordService secretWordService;

    @RequestMapping(method = RequestMethod.GET)
    public String getWords(Model model, Principal principal)
    {
        if(!personService.isAdmin(principal.getName()))
        {
            return "accessDenied";
        }

        List<SecretWord> wordList = secretWordService.findAll();
        model.addAttribute("words", wordList);

        return "admin/word-list";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String addWord(Model model, Principal principal)
    {
        if(!personService.isAdmin(principal.getName()))
        {
            return "accessDenied";
        }

        model.addAttribute("word", new SecretWord());
        model.addAttribute("isNew", "true");


        return "admin/word-form";

    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String saveWord(Principal principal, RedirectAttributes redAttrs,
                              @Valid @ModelAttribute("word") SecretWord secretWord,
                              BindingResult result, Model model)
    {
        if(!personService.isAdmin(principal.getName()))
        {
            return "accessDenied";

        }

        if(result.hasErrors()) {
            redAttrs.addFlashAttribute("result", "fail");
            return "redirect:/admin/words";
        }

        secretWordService.save(secretWord);
        redAttrs.addFlashAttribute("result", "success");
        return "redirect:/admin/words";
    }

    @RequestMapping(
            value = {"/update/{id}"},
            method = {RequestMethod.GET})
    public String updateWord(Model model, Principal principal,
                                @PathVariable("id") int id)
    {
        if(!personService.isAdmin(principal.getName()))
        {
            return "accessDenied";
        }

        SecretWord secretWord = secretWordService.findById(id);
        model.addAttribute("isNew", "false");
        model.addAttribute("word", secretWord);

        return "admin/word-form";
    }

    @RequestMapping(
            value = {"/delete/{id}"},
            method = {RequestMethod.GET})
    public String deleteWord(Model model, @PathVariable("id") int id,
                                RedirectAttributes redAttrs, Principal principal)
    {
        if(!personService.isAdmin(principal.getName()))
        {
            return "accessDenied";
        }

        try {
            secretWordService.delete(secretWordService.findById(id));
        } catch (Exception ex) {
            redAttrs.addFlashAttribute("result", "fail");
            return "redirect:/admin/words";
        }

        redAttrs.addFlashAttribute("result", "success");
        return "redirect:/admin/words";
    }
}
