package com.example.springwebapp.model.request.RequestAdmin;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/1/2023
 * Time: 10:30 AM
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
public class RequestAdminProfile {
    private int id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 chars")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must be less than 1000 chars")
    private String description;

    private String link;

    @NotNull(message = "Admin id is required")
    private int admin_id;
}
