package com.example.springrestful.model.mapper;


import com.example.springrestful.model.entity.Job.Category;
import com.example.springrestful.model.response.ResponseJob.ResponseCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobCategoryMapper {
    @Mapping(source = "category.id", target = "id")
    @Mapping(source = "category.title", target = "title")
    ResponseCategory toResponseCategory(Category category);
    List<ResponseCategory> toResponseCategoryList(List<Category> categories);
}
