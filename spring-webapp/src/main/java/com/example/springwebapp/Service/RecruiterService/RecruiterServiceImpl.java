package com.example.springwebapp.Service.RecruiterService;

import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseRecruiter.ResponseRecruiter;
import com.example.springwebapp.restapi.CommonRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterServiceImpl implements RecruiterService {
    @Autowired
    CommonRestClient commonRestClient;

    private final String apiRecruiter = "http://localhost:8080/api/recruiter";
    @Override
    public ApiResponse<ResponseRecruiter> getRecruiterById(int id) {
        return null;
    }

    @Override
    public ResponseRecruiter findById(int id) {
        return null;
    }

    @Override
    public List<ResponseRecruiter> findAllAccounts() {
        return null;
    }
}
