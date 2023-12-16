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
import com.example.springwebapp.restapi.CommonRestClient;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CandidateServiceImpl implements  CandidateService{
    @Autowired
    CommonRestClient commonRestClient;
    private final String apiProfile = "http://localhost:8080/api/candidate";
    private final String apiApply = "http://localhost:8080/api/candidate/apply";

    private final String apiAbilityProfile = "http://localhost:8080/api/candidate/abilityProfile";
    @Override
    public List<ApiResponse<ResponseCandidate>> getCandidateById(int id) throws Exception {
        String apiGetProfile = "http://localhost:8080/api/candidate/getProfile";
        return commonRestClient.getByCondition(apiGetProfile, id);
    }

    @Override
    public ApiResponse<ResponseCandidate> saveCandidates(RequestCandidate requestCandidate) throws Exception{
        return commonRestClient.post(apiProfile + "/saveCandidates", requestCandidate);
    }


    @Override
    public ApiResponse<ResponseCandidate> removeCandidateById(int id) throws Exception {
        return commonRestClient.deleteByCondition(apiProfile + "/deleteProfileUserById", id);
    }

    @Override
    public List<ApiResponse<ResponseCandidate>> findAllCandidates() throws Exception {
        return commonRestClient.getAll(apiProfile);
    }

    @Override
    public ApiResponse<ResponseCandidate> editCandidate(RequestCandidate requestCandidate) throws Exception {
        return commonRestClient.put(apiProfile+"/editProfile",requestCandidate,requestCandidate.getId());
    }

    @Override
    public void sendEmail(String to, String subject, String content) throws MessagingException {

    }

    @Override
    public void postImg(String url, String img) throws Exception {
        
    }

    @Override
    public ApiResponse<ResponseCandidate> findByEmail(String email) throws Exception {
        return commonRestClient.getByConditionUnique(apiProfile,email);
    }

    @Override
    public List<ApiResponse<ResponseCandidate>> findByServiceContains(String service) throws Exception {
        return (List<ApiResponse<ResponseCandidate>>) commonRestClient.getByConditionUnique(apiProfile,service);
    }

    @Override
    public List<ApiResponse<ResponseCandidate>> findByMajorContains(String major) throws Exception {
        return (List<ApiResponse<ResponseCandidate>>) commonRestClient.getByConditionUnique(apiProfile,major);
    }

    @Override
    public ApiResponse< ResponseAbilityProfile> saveAbilityProfile(RequestAbilityProfile requestAbilityProfile) throws Exception {
        return commonRestClient.post(apiAbilityProfile, requestAbilityProfile);
    }

    @Override
    public ApiResponse< ResponseAbilityProfile> removeAbilityProfileById(String id) throws Exception {
        return commonRestClient.deleteByCondition(apiAbilityProfile,id);
    }

    @Override
    public List<ApiResponse< ResponseAbilityProfile>> findAllAbilityProfile() throws Exception {
        return commonRestClient.getAll(apiAbilityProfile);
    }

    @Override
    public ApiResponse< ResponseAbilityProfile> findAbilityProfileById(String id) throws Exception {
        return commonRestClient.getByConditionUnique(apiAbilityProfile, id);
    }

    @Override
    public ResponseRecruiter followRecruiterById(String id) throws Exception {
        return null;
    }

    @Override
    public ResponseRecruiter cancelFollowRecruiterById(String id) throws Exception {
        return null;
    }

    @Override
    public void contactWithRecruiterById(String id) throws Exception {

    }

    @Override
    public ApiResponse<ResponseInfoApply> applyJob(RequestInfoApply requestInfoApply) throws Exception {
        return commonRestClient.post(apiApply, requestInfoApply);
    }

    @Override
    public ApiResponse<ResponseInfoApply> cancelApplyJob(RequestInfoApply requestInfoApply) throws Exception {
        return commonRestClient.deleteByCondition(apiApply, requestInfoApply.getId());
    }

    @Override
    public ApiResponse<ResponseInfoApply> findInfoApplyById(String id) throws Exception {
        return commonRestClient.getByConditionUnique(apiApply, id);
    }

    @Override
    public List<ResponseInfoApply> findAllCompaniesApplied() throws Exception {
        return null;
    }

    @Override
    public RequestEvaluation evaluateRecruiterById(int id) throws Exception {
        return null;
    }
}
