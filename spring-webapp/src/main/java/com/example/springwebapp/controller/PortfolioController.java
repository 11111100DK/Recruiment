package com.example.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortfolioController {
    @GetMapping(value = "/profile")
    public String getProfileView () {
        return "portfolio";
    }

    @GetMapping(value = "/profile-single")
    public String getProfileSingleView () {
        return "portfolio-single";
    }
}
