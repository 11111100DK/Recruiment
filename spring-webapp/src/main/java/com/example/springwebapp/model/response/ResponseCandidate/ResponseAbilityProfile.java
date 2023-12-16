package com.example.springwebapp.model.response.ResponseCandidate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAbilityProfile {
    private int id;
    private String title;
    private String description;
    private String projectLink;
    private Date dateCreated;
    private Date dateUpdated;
}
