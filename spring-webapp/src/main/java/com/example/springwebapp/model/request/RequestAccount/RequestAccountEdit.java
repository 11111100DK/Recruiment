package com.example.springwebapp.model.request.RequestAccount;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccountEdit {
    private int id;

    @NotEmpty(message = "Username is required")
    @Size(max = 50, message = "Username max is 50 characters")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,20}$"
            , message = "Password must between 6-20 characters. Must contain at least one lower, one upper and one special character")
    private String password;

    @NotEmpty(message = "Role is required")
    @Min(value = 1,message = "Role have value have to >0")
    private int role;

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

    @NotEmpty(message = "Address is required")
    @Size(max = 200, message = "Address max is around 200 characters")
    private String address;

    @NotEmpty(message = "Education is required")
    @Size(max = 50, message = "Education max is around 50 characters")
    private String education;

    @NotEmpty(message = "Description is required")
    @Size(max = 500, message = "Description max is around 500 characters")
    private String description;

    @NotEmpty(message = "Avatar image URL is required")
    @Size(max = 500, message = "Avatar image URL max is around 500 characters")
    private String avatarImg;

    @NotEmpty(message = "Identification card number is required")
    @Size(max = 50, message = "Identification card number max is around 50 characters")
    private String identifyCardNumber;

    @NotEmpty(message = "Identification card name is required")
    @Size(max = 50, message = "Identification card name max is around 50 characters")
    private String identifyCardName;

    @NotNull(message = "Birthday is required")
    private String birthday;
}
