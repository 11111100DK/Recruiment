package com.example.springrestful.model.mapper;


import com.example.springrestful.model.entity.Job.Job;
import com.example.springrestful.model.response.ResponseJob.ResponseJob;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {
    @Mapping(source = "job.account.id", target = "accountId")
    @Mapping(source = "job.category.id", target = "categoryId")
    ResponseJob toResponseJob(Job job);
    List<ResponseJob> toResponseJobList(List<Job> jobs);
}
