package com.example.springwebapp.model.response.ResponseNews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNews {
    private int id;
    private String title;
    private String content;
    private String thumbnail;
    private String dateCreated;
    private String dateUpdated;
    private int viewNumber;
    private int accountId;
}
