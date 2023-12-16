package com.example.springwebapp.model.response.ResponseCandidate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCandidate {
    private int id;
    private String fullName;
    private String nickname;
    private String description;
//    private String linkWebsite;
    private int experienceYear;
    private String major;
    private String service;
    private String email;
    private String phoneNumber;
    private String address;
    private String country;
    private String avata;
    private String cv;
}

