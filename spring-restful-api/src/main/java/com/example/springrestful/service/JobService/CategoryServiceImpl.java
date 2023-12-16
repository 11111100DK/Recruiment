package com.example.springrestful.service.JobService;

import com.example.springrestful.model.entity.Job.Category;
import com.example.springrestful.model.mapper.JobCategoryMapper;
import com.example.springrestful.model.response.ResponseJob.ResponseCategory;
import com.example.springrestful.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    JobCategoryMapper jobCategoryMapper;
    @Override
    public ResponseCategory saveCategory(Category category) {
        return jobCategoryMapper.toResponseCategory(categoryRepository.saveAndFlush(category));
    }

    @Override
    public ResponseCategory findCategoryById(int id) {
        return jobCategoryMapper.toResponseCategory(categoryRepository.findById(id));
    }

    @Override
    public List<ResponseCategory> findAllCategory() {
        return jobCategoryMapper.toResponseCategoryList(categoryRepository.findAll());
    }

    @Override
    public void removeCategoryById(int id) {
        categoryRepository.deleteById(id);
    }
}
