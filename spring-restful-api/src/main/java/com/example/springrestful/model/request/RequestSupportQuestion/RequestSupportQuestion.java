package com.example.springrestful.model.request.RequestSupportQuestion;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestSupportQuestion implements Serializable {
    @Id
    private int id;

    @NotEmpty(message = "Full name is required!")
    private String fullName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email max is around 100 characters")
    private String email;

    @Size(max = 200, message = "Subject max is around 200 characters")
    private String subject;

    @Size(max = 500, message = "Message max is around 500 characters")
    private String message;

    private int accountId;
}
