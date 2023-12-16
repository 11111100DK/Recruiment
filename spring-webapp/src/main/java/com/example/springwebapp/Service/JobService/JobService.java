package com.example.springwebapp.Service.JobService;

import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseJob.ResponseJob;

public interface JobService{
    ApiResponse<ResponseJob> getJobById (int id);
}
