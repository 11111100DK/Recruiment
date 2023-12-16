package com.example.springwebapp.model.request.RequestNews;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateComment {
    private int id;

    @NotBlank(message = "Content is required")
    @Size(max = 1000, message = "Content must be less than 1000 chars")
    private String content;

    private int parentCommentId;

    private int newsId;

    private int accountId;
}
