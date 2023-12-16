package com.example.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {
    @GetMapping(value = "/service")
    public String getServiceView () {
        return "services";
    }

    @GetMapping(value = "/service-single")
    public String getServiceSingleView () {
        return "service-single";
    }

    @GetMapping(value = "/testimonials")
    public String getTestimonials () {
        return "testimonials";
    }
}
