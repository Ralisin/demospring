package it.uniroma2.sc.demospringhibernate.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @PostMapping("/login")
    public ModelAndView handleLogin(
        @RequestParam("username") String username, 
        @RequestParam("password") String password) {
        
        // Controlla se i parametri sono corretti
        if ("user".equals(username) && "pwd".equals(password)) {
            // Se i dati coincidono, reindirizza a una pagina di successo
            ModelAndView modelAndView = new ModelAndView("success");
            modelAndView.addObject("username", username);
            return modelAndView;
        } else {
            // Se i dati non coincidono, mostra un messaggio di errore
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("error", "Username o password errati!");
            return modelAndView;
        }
    }
}
