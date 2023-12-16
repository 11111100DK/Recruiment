package com.example.springwebapp.model.request.RequestAccount;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/15/2023
 * Time: 2:08 PM
 * To change this template use File | Settings | File and Code Templates.
 */

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccountRole {
    private int id;

    @NotEmpty(message = "Name is required")
    @Size(max = 10, message = "Full name max is around 10 characters")
    private String name;
}
