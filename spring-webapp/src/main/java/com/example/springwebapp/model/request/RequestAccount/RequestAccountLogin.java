package com.example.springwebapp.model.request.RequestAccount;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccountLogin {
    @NotEmpty(message = "Username is required")
    @Size(max = 50, message = "Username max is 50 characters")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,20}$"
            , message = "Password must between 6-20 characters. Must contain at least one lower, one upper and one special character")
    private String password;
    private int isAdmin;
}
