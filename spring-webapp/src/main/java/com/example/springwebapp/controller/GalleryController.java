package com.example.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {
    @GetMapping(value = "/gallery")
    public String getGalleryView () {
        return "gallery";
    }


}
