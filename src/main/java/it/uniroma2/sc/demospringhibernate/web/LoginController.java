package it.uniroma2.sc.demospringhibernate.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    /**
     * Handles login form submission.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return ModelAndView object with either the success page or the login page with an error message.
     */
    @PostMapping("/login")
    public ModelAndView handleLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        // Check if the username and password are correct
        if ("user".equals(username) && "pwd".equals(password)) {
            // If correct, redirect to the success page and pass the username
            ModelAndView modelAndView = new ModelAndView("success");
            modelAndView.addObject("username", username);
            return modelAndView;
        } else {
            // If incorrect, redirect to the login page with an error message
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("error", "Username or password incorrect!");
            return modelAndView;
        }
    }
}

/**
 * ModelAndView Explanation:
 *
 * The `ModelAndView` class is used to encapsulate both the model (data) and the view (the page to be rendered) in a single object.
 *
 * - **Model**: This part contains the data that you want to pass to the view. In this example, we use `addObject()` to add
 *   the "username" or "error" to the model. This data will be accessible in the view (e.g., a JSP page).
 *
 * - **View**: This part specifies the name of the view (such as "success" or "index"), which corresponds to the JSP page or template
 *   that will be rendered to the user. Spring will resolve this view name to an actual page.
 *
 * By returning a `ModelAndView` object, Spring handles the view rendering and passes the model data to the appropriate view.
 */
