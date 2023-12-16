package com.example.springrestful.model.entity.Candidate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ability_Profile")
public class AbilityProfile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Title")
    @NotBlank
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Project_Link")
    @NotBlank
    private String projectLink;

    @Column(name = "Date_Created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @Column(name = "Date_Updated")
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;

    @OneToOne(mappedBy = "abilityProfile")
    private Candidate candidate;
}

