package com.example.springrestful.repository;

import com.example.springrestful.model.entity.News.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    News findById (int id);
}
