package com.example.springrestful.model.response.ResponseCandidate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInfoApply {
    private int id;
    private int candidateId;
    private int jobId;
    private Date appliedDate;
    private int status;
}
