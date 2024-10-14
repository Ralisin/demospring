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
        
        // Check if parameters are correct
        if ("user".equals(username) && "pwd".equals(password)) {
            // If they are, redirect to the success page
            ModelAndView modelAndView = new ModelAndView("success");
            modelAndView.addObject("username", username);
            return modelAndView;
        } else {
            // If they are not, show an error message
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("error", "Username or password incorrect!");
            return modelAndView;
        }
    }
}
