package com.example.springrestful.service.NewsService;

import com.example.springrestful.model.request.RequestNews.RequestCreateComment;
import com.example.springrestful.model.request.RequestNews.RequestEditComment;
import com.example.springrestful.model.response.ResponseNews.ResponseComment;

import java.util.List;

public interface CommentService {
    ResponseComment saveComment (RequestCreateComment requestCreateComment) throws Exception;
    ResponseComment saveRepliedComment (RequestCreateComment requestCreateComment) throws Exception;
    ResponseComment editComment (RequestEditComment requestEditComment) throws Exception;
    void removeCommentById (int id) throws Exception;
    ResponseComment findCommentById (int id) throws Exception;
    List<ResponseComment> findAllComments () throws Exception;
    List<ResponseComment> findCommentsByNewsId (int id) throws Exception;
    List<ResponseComment> findCommentsByNewsIdLimit (int id, int limit) throws Exception;
    List<ResponseComment> findNewestCommentsLimit (int limit) throws Exception;
    List<ResponseComment> findReplyCommentsByParentCommentId (int id) throws Exception;
    List<ResponseComment> findReplyCommentsByParentCommentLimit (int id, int limit) throws Exception;
    ResponseComment plusCommentLikeById (int id) throws Exception;
    ResponseComment removeCommentLikeById (int id) throws Exception;

}
