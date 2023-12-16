package com.example.springrestful.service.JobService;
import com.example.springrestful.model.entity.Job.Category;
import com.example.springrestful.model.entity.Job.Job;
import com.example.springrestful.model.request.RequestJob.RequestJob;
import com.example.springrestful.model.response.ResponseJob.ResponseJob;

import java.util.List;

public interface JobService {
    ResponseJob saveJob (RequestJob job) throws Exception;
    void removeJobById (int id) throws Exception;
    List<ResponseJob> findAllJobs () throws Exception;
    ResponseJob findJobById (int id) throws Exception;
    List<ResponseJob> findRandomSomeJobs (int limit) throws Exception;
    List<ResponseJob> findJobByCriteria (String title, String region, String category) throws Exception;
}
