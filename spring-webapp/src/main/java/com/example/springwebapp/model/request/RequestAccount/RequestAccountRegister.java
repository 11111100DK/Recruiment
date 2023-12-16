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
public class RequestAccountRegister {
    @NotEmpty(message = "Username is required")
    @Size(max = 50, message = "Username max is 50 characters")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,20}$"
            , message = "Password must between 6-20 characters. Must contain at least one lower, one upper and one special character")
    private String password;

//    @NotEmpty(message = "Role is required")
//    @Min(value = 1,message = "Role have value have to >0")
    private String role;

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
