package com.example.springrestful.model.entity.Admin;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/1/2023
 * Time: 10:02 AM
 * To change this template use File | Settings | File and Code Templates.
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Title is required")
    @Size(max = 50, message = "Title max is around 50 characters")
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @NotEmpty(message = "Description is required")
    @Size(max = 500, message = "Description max is around 500 characters")
    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "link")
    private String link;

    @NotNull(message = "Date created is required")
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @NotNull(message = "Date updated is required")
    @Column(name = "date_updated", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;

//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "admin_id",referencedColumnName = "id",nullable = false)
//    private Admin adminAdminProfile;
}
