package com.openclassrooms.libraryclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to display homepage
 */
@Controller
@RequestMapping("/")
public class AccueilController {

    /**
     * It displays index.jsp
     *
     * @return name of the requested jsp
     */
    @GetMapping
    public String getAccueil() {
        return "index";
    }
}
