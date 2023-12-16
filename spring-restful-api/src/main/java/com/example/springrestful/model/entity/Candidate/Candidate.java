package com.example.springrestful.model.entity.Candidate;

import com.example.springrestful.model.entity.Account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Candidate")
public class Candidate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotBlank
    @Column(name = "Full_Name")
    private String fullName;

    @NotBlank
    @Column(name = "Nick_Name", unique = true)
    private String nickname;

    @NotNull
    private String description;

//    @NotNull
//    @Column(name = "Link_Website")
//    private String linkWebsite;

    @NotNull
    @Column(name = "Experience_Year")
    private int experienceYear;

    @NotNull
    @Column(name = "Phone_Number")
    private String phoneNumber;

    @NotBlank
    @Column(name = "Major")
    private String major;

    @Column(name = "Service")
    private String service;

    @NotNull
    @Column(name = "Address")
    private String address;

    @Column(name = "Country")
    private String country;

    @Column(name = "avata", unique = false, nullable = false, length = 100000)
    private String avata;

    @Column(name = "cv", unique = false, nullable = false, length = 100000)
    private String cv;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email max is around 100 characters")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "Ability_Profile_ID")
    private AbilityProfile abilityProfile;

    @OneToOne
    @JoinColumn(name = "Account_ID")
    private Account account;

    @OneToMany(mappedBy = "candidate")
    private List<InfoApply> infoApplyList = new ArrayList<>();


}
