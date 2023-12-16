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
public class RequestInfoApply {

    private int id;
    private int candidateId;
    private int jobId;


    @Temporal(TemporalType.DATE)
    @NotBlank
    private Date appliedDate;

    @NotNull
    private int status;

}
