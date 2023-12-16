package com.example.springrestful.model.request.RequestCandidate;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAbilityProfile {
    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String projectLink;

    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;

    @NotNull
    private int status;
}
