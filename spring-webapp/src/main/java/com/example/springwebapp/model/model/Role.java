package com.example.springwebapp.model.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private int id;

    private String name;

    private int admin_manage;

    private int account_manage;

    private int role_manage;
}