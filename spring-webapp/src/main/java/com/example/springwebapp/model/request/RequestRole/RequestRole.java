package com.example.springwebapp.model.request.RequestRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRole {
    private int id;
    private String name;

    private int admin_manage;
    private int account_manage;
    private int role_manage;
}
