package com.example.springrestful.repository;

import com.example.springrestful.model.entity.Job.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findById (int id);
}
