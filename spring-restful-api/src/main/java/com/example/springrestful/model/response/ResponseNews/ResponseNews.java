package com.example.springrestful.model.response.ResponseNews;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private String status;
    private int accountId;
}
