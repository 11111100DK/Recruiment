package com.example.springwebapp.model.response.ResponseRole;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRole {
    private int id;

    private String name;

    private int admin_manage;

    private int account_manage;

    private int role_manage;
}
