package com.openclassrooms.libraryclient.controller;

import com.openclassrooms.libraryclient.model.JwtResponse;
import com.openclassrooms.libraryclient.model.LoginForm;
import com.openclassrooms.libraryclient.model.User;
import com.openclassrooms.libraryclient.proxy.AuthProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Controller to display login page
 *
 * @see AuthProxy
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthProxy authProxy;

    /**
     * It displays login.jsp
     *
     * @param model model which supply attributes used for rendering views
     *
     * @return name of the requested jsp
     */
    @GetMapping
    public String getLoginPage(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    /**
     * Method to authenticate the user
     *
     * @param loginForm login form which contains username and password
     * @param session the session
     *
     * @return redirect to the homepage
     */
    @PostMapping
    public ModelAndView authenticateUser(@ModelAttribute LoginForm loginForm, HttpSession session) {
        JwtResponse response = authProxy.authenticateUser(loginForm).getBody();
        session.setAttribute("auth-token", response.getToken());
        User user = new User(response.getId(), response.getUsername(), response.getEmail());
        session.setAttribute("user", user);
        return new ModelAndView("redirect:/");
    }
}
