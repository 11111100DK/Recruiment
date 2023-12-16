package com.example.springrestful.model.request.RequestAccount;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/15/2023
 * Time: 2:33 PM
 * To change this template use File | Settings | File and Code Templates.
 */

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccountRole {
    private int id;

    @NotEmpty(message = "Username is required")
    @Size(max = 50, message = "Username max is 50 characters")
    private String name;
}
