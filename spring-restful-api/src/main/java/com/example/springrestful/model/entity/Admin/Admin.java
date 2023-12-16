package com.example.springrestful.model.entity.Admin;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/1/2023
 * Time: 10:01 AM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springrestful.model.entity.Account.Account;
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
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @NotEmpty(message = "Full name is required")
//    @Size(max = 50, message = "Full name max is 50 characters")
//    @Column(name = "full_name", nullable = false, length = 50)
//    private String fullName;

    @NotEmpty(message = "Description is required")
    @Size(max = 500, message = "Description max is around 500 characters")
    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "experience_year",nullable = false,columnDefinition = "INT CHECK(experience_year>=0 AND experience_year<=10)")
    private int experienceYear;

    @NotEmpty(message = "Major is required")
    @Size(max = 50, message = "Major max is around 50 characters")
    @Column(name = "major", nullable = false, length = 50)
    private String major;

    @NotEmpty(message = "Role is required")
    @Size(max = 10, message = "Role max is around 10 characters")
    @Column(name = "role", nullable = false, length = 10)
    private String role;

    @Column(name = "status", nullable = false,columnDefinition = "INT DEFAULT(0)")
    private int status;

    @Column(name = "managed_by")
    private Integer managedBy;

//    @Column(name = "activeSession")
//    private int activeSession;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account accountAdmin;

//    @OneToMany(mappedBy = "adminAdminProfile")
//    private List<AdminProfile> adminProfileList;

}
