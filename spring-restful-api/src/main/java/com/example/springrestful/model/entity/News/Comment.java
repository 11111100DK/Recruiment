package com.example.springrestful.model.entity.News;

import com.example.springrestful.model.entity.Account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Content is required")
    @Size(max = 1000, message = "Content must be less than 1000 chars")
    @Column(name = "content", length = 1000, nullable = false)
    private String content;

    @NotNull(message = "Date created is required")
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @NotNull(message = "Date updated is required")
    @Column(name = "date_updated", nullable = false)
    private Date dateUpdated;

    @Min(value = 0, message = "Like number must be greater than or equal to 0")
    @Column(name = "like_number", nullable = false)
    private int likeNumber;

    @Column(name = "parent_comment_id")
    private int parentCommentId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

}