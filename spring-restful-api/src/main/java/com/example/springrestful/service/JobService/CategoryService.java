package com.example.springrestful.service.JobService;

import com.example.springrestful.model.entity.Job.Category;
import com.example.springrestful.model.response.ResponseJob.ResponseCategory;

import java.util.List;

public interface CategoryService {
    ResponseCategory saveCategory(Category category);
    ResponseCategory findCategoryById (int id);
    List<ResponseCategory> findAllCategory ();
    void removeCategoryById (int id);
}
