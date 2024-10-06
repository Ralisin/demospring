package it.uniroma2.sc.demospringhibernate.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspController {

    @GetMapping("/jsp")
    public String showJspPage() {
        return "index"; // nome del file JSP senza estensione
    }
}
