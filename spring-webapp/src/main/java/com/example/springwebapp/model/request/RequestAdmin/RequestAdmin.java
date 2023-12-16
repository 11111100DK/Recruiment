package com.example.springwebapp.model.request.RequestAdmin;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/1/2023
 * Time: 10:29 AM
 * To change this template use File | Settings | File and Code Templates.
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAdmin {
    private int id;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must be less than 1000 chars")
    private String description;

    private int experienceYear;

    @NotBlank(message = "Major is required")
    private String major;

    @NotBlank(message = "Major is required")
    private String role;

    private int status;

    @NotNull(message = "Account id is required")
    private int accountId;
}
