package com.example.springrestful.model.entity.Job;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 chars")
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @OneToMany(mappedBy = "category")
    private List<Job> jobs;
}
