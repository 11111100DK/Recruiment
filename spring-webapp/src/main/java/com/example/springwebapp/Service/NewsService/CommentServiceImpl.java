package com.example.springwebapp.Service.NewsService;

import com.example.springwebapp.model.request.RequestNews.RequestCreateComment;
import com.example.springwebapp.model.request.RequestNews.RequestEditComment;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseNews.ResponseComment;
import com.example.springwebapp.restapi.CommonRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommonRestClient commonRestClient;
    private final String apiComment = "http://localhost:8080/api/comments";
    private final String apiReplyComment = "http://localhost:8080/api/comments/reply";

    @Override
    public ApiResponse<ResponseComment> saveComment(RequestCreateComment requestCreateComment) throws Exception {
        return commonRestClient.post(apiComment + "/save", requestCreateComment);
    }

    @Override
    public ApiResponse<ResponseComment> saveReplyComment(RequestCreateComment requestCreateComment) throws Exception {
        return commonRestClient.post(apiComment + "/save-reply", requestCreateComment);
    }

    @Override
    public ApiResponse<ResponseComment> editComment(RequestEditComment requestEditComment) throws Exception {
        return commonRestClient.put(apiComment, requestEditComment, requestEditComment.getId());
    }

    @Override
    public ApiResponse<ResponseComment> removeCommentById(int id) throws Exception {
        return commonRestClient.deleteByCondition(apiComment, id);
    }

    @Override
    public ApiResponse<ResponseComment> findCommentById(int id) throws Exception {
        return commonRestClient.getByConditionUnique(apiComment, id);
    }

    @Override
    public List<Object> findCommentByNewsIdLimit(int newsId, int limit) throws Exception {
        return commonRestClient.getByCondition(apiComment + "/newsId/", newsId + "/" + limit);
    }

    @Override
    public List<Object> findReplyCommentByParentId(int parentCommentId, int limit) throws Exception {
        return commonRestClient.getByCondition(apiReplyComment, "/" + parentCommentId + "/" + limit);
    }
}
