package com.example.springwebapp.model.request.RequestJob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestJob {
    private int id;
    private String title;
    private String image;
    private String job;
    private String employmentStatus;
    private String city;
    private String region;
    private String locationDetail;
    private int vacancy;
    private Date dateBegin;
    private Date dateEnd;
    private Date applicationDeadline;
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
