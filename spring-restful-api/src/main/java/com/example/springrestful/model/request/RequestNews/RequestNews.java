package com.example.springrestful.model.request.RequestNews;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestNews {

    private int id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 chars")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    @NotBlank(message = "thumbnail is required")
    private String thumbnail;

    private int accountId;
}
