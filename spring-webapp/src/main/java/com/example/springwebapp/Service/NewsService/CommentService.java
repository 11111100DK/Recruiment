package com.example.springwebapp.Service.NewsService;

import com.example.springwebapp.model.request.RequestNews.RequestCreateComment;
import com.example.springwebapp.model.request.RequestNews.RequestEditComment;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseNews.ResponseComment;

import java.util.List;

public interface CommentService {
    ApiResponse<ResponseComment> saveComment (RequestCreateComment requestCreateComment) throws Exception;
    ApiResponse<ResponseComment> saveReplyComment (RequestCreateComment requestCreateComment) throws Exception;
    ApiResponse<ResponseComment> editComment (RequestEditComment requestEditComment) throws Exception;
    ApiResponse<ResponseComment> removeCommentById (int id) throws Exception;
    ApiResponse<ResponseComment> findCommentById (int id) throws Exception;
    List<Object> findCommentByNewsIdLimit (int newsId, int limit) throws Exception;
    List<Object> findReplyCommentByParentId (int parentCommentId, int limit) throws Exception;
}
