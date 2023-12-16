package com.example.springwebapp.model.response.ResponseJob;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseJob {
    private int id;
    private String title;
    private String image;
    private String job;
    private String employmentStatus;
    private String city;
    private String region;
    private String locationDetail;
    private int vacancy;
    private String dateBegin;
    private String dateEnd;
    private String applicationDeadline;
    private double salary;
    private int experienceYear;
    private String description;
    private String responsibility;
    private String education;
    private String benefit;
    private String skillRequired;
    private int accountId;
    private int categoryId;
}