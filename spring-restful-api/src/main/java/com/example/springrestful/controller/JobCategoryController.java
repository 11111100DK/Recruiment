package com.example.springrestful.controller;


import com.example.springrestful.exception.ApplicationException;
import com.example.springrestful.exception.NotFoundException;
import com.example.springrestful.exception.ValidationException;
import com.example.springrestful.model.entity.Job.Category;
import com.example.springrestful.model.entity.Job.Job;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import com.example.springrestful.model.response.ResponseJob.ResponseCategory;
import com.example.springrestful.model.response.ResponseNews.ResponseNews;
import com.example.springrestful.service.JobService.CategoryService;
import com.example.springrestful.util.ValidatorUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class JobCategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ValidatorUtil validatorUtil;

    @GetMapping(value = "")
    public ResponseEntity<ApiResponse<List<ResponseCategory>>> returnAllCategory() throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.ok(categoryService.findAllCategory());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<ResponseCategory>> returnCategoryById(@PathVariable int id) throws Exception {
        try {
            if (categoryService.findCategoryById(id) == null) {
                throw new NotFoundException("Category not found");
            }
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(categoryService.findCategoryById(id));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<ResponseCategory>> addCategory(@RequestParam String title) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (title == null || title.isEmpty()) {
                throw new NotFoundException("Category cannot be blank");
            }

            Category category = new Category();
            category.setTitle(title);
            ResponseCategory saveCategory = categoryService.saveCategory(category);
            apiResponse.ok(saveCategory);
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
    public ResponseEntity<ApiResponse> deleteCategoryJobById(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            ResponseCategory deleteCategory = categoryService.findCategoryById(id);
            if (deleteCategory == null) {
                throw new NotFoundException("Category not found");
            }
            categoryService.removeCategoryById(id);
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
