package com.example.springrestful.repository;

import com.example.springrestful.model.entity.News.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findById (int id);

    @Query("SELECT c FROM Comment c Where c.parentCommentId = :parentCommentId")
    List<Comment> findByParentId (@Param(value = "parentCommentId") int parentCommentId);

    @Query("SELECT c FROM Comment c Where c.news.id = :newsId AND c.parentCommentId = 0 ORDER BY c.dateUpdated DESC")
    List<Comment> findByNewsId (@Param(value = "newsId") int newsId);
}
