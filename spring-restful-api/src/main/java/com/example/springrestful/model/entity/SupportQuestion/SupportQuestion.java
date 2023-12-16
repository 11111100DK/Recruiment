package com.example.springrestful.model.entity.SupportQuestion;

import com.example.springrestful.model.entity.Account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "support_questions")
public class SupportQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Full name is required!")
    @Column(name = "full_name")
    private String fullName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email max is around 100 characters")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 200, message = "Subject max is around 200 characters")
    @Column(name = "subject", nullable = false, length = 200)
    private String subject;

    @Size(max = 500, message = "Message max is around 500 characters")
    @Column(name = "message", nullable = false, length = 500)
    private String message;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
