package com.example.springrestful.service.SupportAndContactService;

import com.example.springrestful.model.entity.SupportQuestion.SupportQuestion;
import com.example.springrestful.model.request.RequestSupportQuestion.RequestSupportQuestion;
import com.example.springrestful.model.response.ResponseSupportQuestion.ResponseSupportQuestion;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface SupportAndContactService {
    List<SupportQuestion> findAll();
    List<SupportQuestion> findByAccount(int accountId);
    SupportQuestion findById(int id);
    ResponseSupportQuestion saveQuestion(RequestSupportQuestion requestSupportQuestion, BindingResult bindingResult);
    ResponseSupportQuestion updateQuestion(RequestSupportQuestion requestSupportQuestion, BindingResult bindingResult);
    void deleteQuestionById (int id);
    List<RequestSupportQuestion> findQuestionByAccountUserName (String username) throws Exception;
    List<RequestSupportQuestion> findQuestionByAccountEmail (String email) throws Exception;
}
