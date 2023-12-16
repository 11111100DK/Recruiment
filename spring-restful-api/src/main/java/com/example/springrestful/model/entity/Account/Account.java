package com.example.springrestful.model.entity.Account;

import com.example.springrestful.model.entity.Admin.Admin;
import com.example.springrestful.model.entity.Job.Job;
import com.example.springrestful.model.entity.News.Comment;
import com.example.springrestful.model.entity.News.News;
import com.example.springrestful.model.entity.Role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Username is required")
    @Size(max = 50, message = "Username max is 50 characters")
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "status", nullable = false)
    private int status;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email max is around 100 characters")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotEmpty(message = "Phone number is required")
    @Size(max = 20, message = "Phone number max is around 20 characters")
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @NotEmpty(message = "Full name is required")
    @Size(max = 100, message = "Full name max is around 100 characters")
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @NotEmpty(message = "Address is required")
    @Size(max = 200, message = "Address max is around 200 characters")
    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @NotEmpty(message = "Education is required")
    @Size(max = 50, message = "Education max is around 50 characters")
    @Column(name = "education", nullable = false, length = 50)
    private String education;

    @NotEmpty(message = "Description is required")
    @Size(max = 500, message = "Description max is around 500 characters")
    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @NotNull(message = "Date of sign-in is required")
    @Column(name = "date_sign_in", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateSignIn;

    @NotEmpty(message = "Avatar image URL is required")
    @Size(max = 500, message = "Avatar image URL max is around 500 characters")
    @Column(name = "avatar_img", nullable = false, length = 500)
    private String avatarImg;

    @NotEmpty(message = "Identification card number is required")
    @Size(max = 50, message = "Identification card number max is around 50 characters")
    @Column(name = "identify_card_number", nullable = false, length = 50)
    private String identifyCardNumber;

    @NotEmpty(message = "Identification card name is required")
    @Size(max = 50, message = "Identification card name max is around 50 characters")
    @Column(name = "identify_card_name", nullable = false, length = 50)
    private String identifyCardName;

//    @NotNull(message = "Birthday is required")
    @Column(name = "birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "role")
    private String role;

    @Min(0)
    @Column(name = "followers")
    private int followers;

    @Min(0)
    @Column(name = "following")
    private int following;

    @OneToMany(mappedBy = "account")
    private List<News> newsList;

    @OneToMany(mappedBy = "account")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "accountAdmin")
    private List<Admin> adminList;

    @OneToMany(mappedBy = "account")
    private List<Job> jobs;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role_id;



}