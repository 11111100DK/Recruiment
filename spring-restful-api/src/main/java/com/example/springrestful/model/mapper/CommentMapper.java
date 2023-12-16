package com.example.springrestful.model.mapper;

import com.example.springrestful.model.entity.News.Comment;
import com.example.springrestful.model.response.ResponseNews.ResponseComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "comment.id", target = "id")
    @Mapping(source = "comment.content", target = "content")
    @Mapping(source = "comment.dateCreated", target = "dateCreated")
    @Mapping(source = "comment.dateUpdated", target = "dateUpdated")
    @Mapping(source = "comment.likeNumber", target = "likeNumber")
    @Mapping(source = "comment.parentCommentId", target = "parentCommentId")
    @Mapping(source = "comment.account.id", target = "accountId")
    @Mapping(source = "comment.account.username", target = "accountUsername")
    @Mapping(source = "comment.news.id", target = "newsId")
    ResponseComment toResponseComment(Comment comment);
    List<ResponseComment> toResponseCommentList(List<Comment> comments);
}
