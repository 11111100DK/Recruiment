package com.example.springwebapp.Service.NewsService;

import com.example.springwebapp.model.request.RequestCandidate.RequestAbilityProfile;
import com.example.springwebapp.model.request.RequestCandidate.RequestCandidate;
import com.example.springwebapp.model.request.RequestCandidate.RequestInfoApply;
import com.example.springwebapp.model.request.RequestRecruiter.RequestEvaluation;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseCandidate.ResponseAbilityProfile;
import com.example.springwebapp.model.response.ResponseCandidate.ResponseCandidate;
import com.example.springwebapp.model.response.ResponseCandidate.ResponseInfoApply;
import com.example.springwebapp.model.response.ResponseRecruiter.ResponseRecruiter;
import jakarta.mail.MessagingException;


import java.util.List;

public interface CandidateService {
    List<ApiResponse<ResponseCandidate>> getCandidateById(int id) throws Exception;


    ApiResponse<ResponseCandidate> saveCandidates(RequestCandidate requestCandidate) throws Exception;
    ApiResponse< ResponseCandidate> removeCandidateById (int id) throws Exception;
    List<ApiResponse <ResponseCandidate>> findAllCandidates () throws Exception;
//    ApiResponse< ResponseCandidate> findCandidateById (String id) throws Exception;
    ApiResponse< ResponseCandidate> editCandidate(RequestCandidate requestCandidate) throws Exception;
    void sendEmail(String to, String subject, String content) throws MessagingException;
    void postImg(String url, String img)throws Exception;



    ApiResponse< ResponseCandidate> findByEmail(String email) throws Exception;
    List<ApiResponse< ResponseCandidate>> findByServiceContains(String service) throws Exception;
    List<ApiResponse< ResponseCandidate>> findByMajorContains(String major) throws Exception;


    ApiResponse< ResponseAbilityProfile> saveAbilityProfile (RequestAbilityProfile requestAbilityProfile) throws Exception;
    ApiResponse< ResponseAbilityProfile> removeAbilityProfileById (String id) throws Exception;
    List<ApiResponse< ResponseAbilityProfile>> findAllAbilityProfile () throws Exception;
    ApiResponse< ResponseAbilityProfile> findAbilityProfileById (String id) throws Exception;


    ResponseRecruiter followRecruiterById (String id) throws Exception;
    ResponseRecruiter cancelFollowRecruiterById (String id) throws Exception;
    void contactWithRecruiterById (String id) throws Exception;


    ApiResponse< ResponseInfoApply> applyJob (RequestInfoApply requestInfoApply) throws Exception;
    ApiResponse< ResponseInfoApply> cancelApplyJob (RequestInfoApply requestInfoApply) throws Exception;
    ApiResponse< ResponseInfoApply> findInfoApplyById (String id) throws Exception;
    List<ResponseInfoApply> findAllCompaniesApplied() throws Exception;



    RequestEvaluation evaluateRecruiterById (int id) throws Exception;
}
