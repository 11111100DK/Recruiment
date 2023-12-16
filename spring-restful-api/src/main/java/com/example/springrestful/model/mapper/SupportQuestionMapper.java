package com.example.springrestful.model.mapper;
import com.example.springrestful.model.entity.SupportQuestion.SupportQuestion;
import com.example.springrestful.model.request.RequestSupportQuestion.RequestSupportQuestion;
import com.example.springrestful.model.response.ResponseSupportQuestion.ResponseSupportQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupportQuestionMapper {
    // Map entity to response
    @Mapping(source = "supportQuestion.id", target = "id")
    @Mapping(source = "supportQuestion.fullName", target = "fullName")
    @Mapping(source = "supportQuestion.email", target = "email")
    @Mapping(source = "supportQuestion.subject", target = "subject")
    @Mapping(source = "supportQuestion.message", target = "message")
    @Mapping(source = "supportQuestion.account.id", target = "accountId")

    ResponseSupportQuestion toResponseSupportQuestion(SupportQuestion supportQuestion);
    List<ResponseSupportQuestion> toResponseSupportQuestionList(List<SupportQuestion> supportQuestions);
    List<RequestSupportQuestion> toRequestSupportQuestionList(List<SupportQuestion> supportQuestions);

    // Map request to entity
    SupportQuestion toEntity(RequestSupportQuestion requestSupportQuestion);

}
