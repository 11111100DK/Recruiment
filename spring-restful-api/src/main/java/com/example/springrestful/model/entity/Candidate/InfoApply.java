package com.example.springrestful.model.entity.Candidate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Info_Apply")
public class InfoApply implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Applied_Date")
    @Temporal(TemporalType.DATE)
    private Date appliedDate;

    @Column(name = "Status")
    @NotNull
    private int status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Candidate_ID")
    private Candidate candidate;


//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "Job_ID")
//    private Job job;
}
