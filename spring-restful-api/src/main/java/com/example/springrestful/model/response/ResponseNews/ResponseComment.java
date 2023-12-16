package com.example.springrestful.model.response.ResponseNews;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseComment {
    private int id;

    private String content;

    private Date dateCreated;

    private Date dateUpdated;

    private int likeNumber;

    private int parentCommentId;

    private int accountId;

    private String accountUsername;

    private int newsId;
}
