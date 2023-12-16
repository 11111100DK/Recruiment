package com.example.springrestful.model.request.RequestAccount;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccountRegister {
    @NotEmpty(message = "Username is required")
    @Size(max = 50, message = "Username max is 50 characters")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,20}$"
            , message = "Password must between 6-20 characters. Must contain at least one lower, one upper and one special character")
    private String password;

//    @NotEmpty(message = "Role is required")
    private String role;

    private int role_id;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email max is around 100 characters")
    private String email;

    @NotEmpty(message = "Phone number is required")
    @Size(max = 20, message = "Phone number max is around 20 characters")
    private String phoneNumber;

    @NotEmpty(message = "Full name is required")
    @Size(max = 100, message = "Full name max is around 100 characters")
    private String fullName;
}
