package com.example.springrestful.model.entity.Account;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/15/2023
 * Time: 8:13 AM
 * To change this template use File | Settings | File and Code Templates.
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name is required")
    @Size(max = 10, message = "Full name max is around 10 characters")
    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "account_manage", nullable = false)
    private int account_manage;

    @Column(name = "role_manage", nullable = false)
    private int role_manage;

//    @OneToMany(mappedBy = "role")
//    private List<Account> accountList;
}
