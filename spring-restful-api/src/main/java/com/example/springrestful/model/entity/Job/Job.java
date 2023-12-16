package com.example.springrestful.model.entity.Job;

import com.example.springrestful.model.entity.Account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private int id;

    @Column(name = "job_title", nullable = false)
    private String title;

    @Column(name = "image_url")
    private String image;

    @Column(name = "job_type", nullable = false)
    private String job;

    @Column(name = "employment_status")
    private String employmentStatus;

    @Column(name = "job_city")
    private String city;

    @Column(name = "job_region")
    private String region;

    @Column(name = "location_detail")
    private String locationDetail;

    @Column(name = "vacancy_count")
    private int vacancy;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date dateBegin;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date dateEnd;

    @Temporal(TemporalType.DATE)
    @Column(name = "application_deadline")
    private Date applicationDeadline;

    @Column(name = "salary_amount")
    private double salary;

    @Column(name = "required_experience_years")
    private int experienceYear;

    @Column(name = "job_description", columnDefinition = "longtext")
    private String description;

    @Column(name = "job_responsibility", columnDefinition = "longtext")
    private String responsibility;

    @Column(name = "required_education")
    private String education;

    @Column(name = "job_benefit", columnDefinition = "longtext")
    private String benefit;

    @Column(name = "required_skills", columnDefinition = "longtext")
    private String skillRequired;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
