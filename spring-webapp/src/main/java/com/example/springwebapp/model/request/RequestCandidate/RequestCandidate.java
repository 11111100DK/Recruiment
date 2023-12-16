package com.example.springwebapp.model.request.RequestCandidate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCandidate {
    private int id;

    @NotNull
    private String fullName;

    @NotNull
    private String nickname;

    @NotNull
    private String description;

//    @NotNull
//    private String linkWebsite;

    @NotNull
    private int experienceYear;

    @NotNull
    private String major;

    @NotNull
    private String service;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private String country;

//    @NotNull
    private MultipartFile avata;
    private MultipartFile cv;
}


