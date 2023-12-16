package com.example.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping(value = "/login")
    public String getLoginView () {
        return "login";
    }

    @GetMapping(value = "/register")
    public String getRegisterView () {
        return "register";
    }


}
