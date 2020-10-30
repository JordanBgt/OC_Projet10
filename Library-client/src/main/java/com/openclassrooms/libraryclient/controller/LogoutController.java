package com.openclassrooms.libraryclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Controller to logout the user
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

    /**
     * Logout the user : invalidate the session
     *
     * @param session the session
     *
     * @return redirect to the homepage
     */
    @GetMapping
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }
}
