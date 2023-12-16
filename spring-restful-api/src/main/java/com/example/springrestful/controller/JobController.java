package com.example.springrestful.controller;

import com.example.springrestful.exception.ApplicationException;
import com.example.springrestful.exception.NotFoundException;
import com.example.springrestful.exception.ValidationException;
import com.example.springrestful.model.entity.Job.Job;
import com.example.springrestful.model.request.RequestJob.RequestJob;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import com.example.springrestful.model.response.ResponseJob.ResponseJob;
import com.example.springrestful.repository.AccountRepository;
import com.example.springrestful.service.JobService.CategoryService;
import com.example.springrestful.service.JobService.JobService;
import com.example.springrestful.util.ValidatorUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    JobService jobService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CategoryService categoryService;

    @Autowired
    ValidatorUtil validatorUtil;

    @GetMapping(value = "")
    public ResponseEntity<ApiResponse<List<ResponseJob>>> returnAllJobs() throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.ok(jobService.findAllJobs());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping(value = "/limit/{limit}")
    public ResponseEntity<ApiResponse<List<ResponseJob>>> returnRandomLimitJobs(@PathVariable int limit) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (limit < 0) {
                throw new Exception("Limit cannot be negative");
            }

            apiResponse.ok(jobService.findRandomSomeJobs(limit));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping(value = "/criteria")
    public ResponseEntity<ApiResponse<List<ResponseJob>>> returnJobByCriteria(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "region", required = false) String region,
            @RequestParam(name = "category", required = false) String category) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.ok(jobService.findJobByCriteria(title, region, category));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<ResponseJob>> returnJobById(@PathVariable int id) throws Exception {
        try {
            if (jobService.findJobById(id) == null) {
                throw new NotFoundException("Job not found");
            }
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(jobService.findJobById(id));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<ResponseJob>> addJob(@Valid @RequestBody RequestJob requestJob, BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            if (requestJob == null) {
                throw new NotFoundException("Job cannot be blank");
            }
            apiResponse.ok(jobService.saveJob(requestJob));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJobById(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            ResponseJob job = jobService.findJobById(id);
            if (job == null) {
                throw new NotFoundException("Job not found");
            }
            jobService.removeJobById(id);
            apiResponse.ok("Delete successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }
}
