package com.example.springwebapp.model.response.ResponseCandidate;

import com.example.springwebapp.model.response.ResponseJob.ResponseJob;
import com.example.springwebapp.model.response.ResponseRecruiter.ResponseRecruiter;
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
    ResponseRecruiter recruiterName;
    ResponseRecruiter recruiterLogo;
    ResponseJob jobTitle;
    ResponseJob jobCity;
}

