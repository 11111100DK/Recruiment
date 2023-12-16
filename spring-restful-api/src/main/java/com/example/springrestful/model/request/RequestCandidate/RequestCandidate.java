package com.example.springrestful.model.request.RequestCandidate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCandidate{
    private int id;

    @NotNull
    private String fullName;

    @NotNull
    private String nickname;

    @NotNull
    private String description;

    @NotNull
    private int experienceYear;

    @NotNull
    private String major;

    @NotNull
    private String service;

    @NotNull
    private String email;

    @NotNull
    private  String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private String country;

    @NotNull
    private MultipartFile avata;
    @NotNull
    private MultipartFile cv;


    public RequestCandidate(int id, String fullName, String nickname, String description, String linkWebsite, int experienceYear, String major, String service, String email, String phoneNumber, String address, String country) {
        this.id = id;
        this.fullName = fullName;
        this.nickname = nickname;
        this.description = description;
        this.experienceYear = experienceYear;
        this.major = major;
        this.service = service;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
    }
}
