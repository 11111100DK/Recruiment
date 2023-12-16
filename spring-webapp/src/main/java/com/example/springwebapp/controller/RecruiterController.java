package com.example.springwebapp.controller;

import com.example.springwebapp.Service.RecruiterService.RecruiterService;

import com.example.springwebapp.model.request.RequestRecruiter.RequestRecruiter;
import com.example.springwebapp.model.response.ResponseRecruiter.ResponseRecruiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.LinkedHashMap;
import java.util.List;

@Controller

public class RecruiterController {
    @Autowired
    RecruiterService recruiterService;

    @GetMapping(value = "/recruiter_listings")
    public String getRecruiterListView () {
        return "recruiter_listings";
    }


    @GetMapping(value = "/post_recruiter")
    public String getRecruiterView () {
        return "post_recruiter";
    }

    @GetMapping(value = "/recruiter_single")
    public String getRecruiterList () {
        return "recruiter_single";
    }

}
