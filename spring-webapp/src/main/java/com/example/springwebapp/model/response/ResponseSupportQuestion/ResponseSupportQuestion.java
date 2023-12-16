package com.example.springwebapp.model.response.ResponseSupportQuestion;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
public class ResponseSupportQuestion implements Serializable {
    // support question
    private int id;
    private String fullName;
    private String email;
    private String subject;
    private String message;
    // account
    private int accountId;
}
