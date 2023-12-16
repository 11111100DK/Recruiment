package com.example.springwebapp.model.request.RequestAccount;

import jakarta.validation.constraints.Email;
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
public class RequestRegisterOTP {
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,20}$"
            , message = "Password must between 6-20 characters. Must contain at least one lower, one upper and one special character")

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,20}$"
            , message = "Password must between 6-20 characters. Must contain at least one lower, one upper and one special character")
    private String password;

    private String confirmPassword;

    private String role;

    private String email;

}
