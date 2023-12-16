package com.example.springrestful.model.entity.Role;

import com.example.springrestful.model.entity.Account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "admin_manage", nullable = false)
    private int admin_manage;

    @Column(name = "account_manage", nullable = false)
    private int account_manage;

    @Column(name = "role_manage", nullable = false)
    private int role_manage;

//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    private List<Account> accountList;

}
