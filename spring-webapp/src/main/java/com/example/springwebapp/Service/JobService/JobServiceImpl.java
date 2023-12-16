package com.example.springwebapp.Service.JobService;

import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseJob.ResponseJob;
import com.example.springwebapp.restapi.CommonRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    CommonRestClient commonRestClient;

    private final String apiJob = "http://localhost:8080/api/jobs";

    @Override
    public ApiResponse<ResponseJob> getJobById(int id) {
        return commonRestClient.getByConditionUnique(apiJob, id);
    }
}
