package com.example.springwebapp.model.request.RequestCandidate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPartialCandidate {
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

}
