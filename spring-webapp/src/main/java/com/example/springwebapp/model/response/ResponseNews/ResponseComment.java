package com.example.springwebapp.model.response.ResponseNews;

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

    private String dateCreated;

    private String dateUpdated;

    private int likeNumber;

    private int parentCommentId;

    private int accountId;

    private String accountUsername;

    private int newsId;
}
