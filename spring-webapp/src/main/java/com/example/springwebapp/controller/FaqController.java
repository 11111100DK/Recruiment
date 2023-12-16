package com.example.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqController {
    @GetMapping(value = "/fag")
    public String getFaqView () {
        return "faq";
    }
}
