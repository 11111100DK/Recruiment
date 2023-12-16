package com.example.springwebapp.controller;

import com.example.springwebapp.Service.JobService.JobService;
import com.example.springwebapp.model.Mapper.JobMapper;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ApiResponse.StatusEnum;
import com.example.springwebapp.model.response.ResponseJob.ResponseJob;
import com.example.springwebapp.model.response.ResponseNews.ResponseNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping(value = "/job-list")
    public String getJobListView () {
        return "job-listings";
    }

    @GetMapping(value = "/job-single")
    public String getReciew () {
        return "job-single";
    }

    @GetMapping(value = "/post-job")
    public String getPostJobView () {
        return "post-job";
    }
}
