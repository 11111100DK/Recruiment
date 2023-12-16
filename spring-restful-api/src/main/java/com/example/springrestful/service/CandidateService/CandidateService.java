package com.example.springrestful.service.CandidateService;

import com.example.springrestful.model.request.RequestCandidate.RequestAbilityProfile;
import com.example.springrestful.model.request.RequestCandidate.RequestCandidate;
import com.example.springrestful.model.request.RequestCandidate.RequestInfoApply;
import com.example.springrestful.model.request.RequestCandidate.RequestPartialCandidate;
import com.example.springrestful.model.request.RequestJob.RequestJob;
import com.example.springrestful.model.request.RequestRecruiter.RequestEvaluation;
import com.example.springrestful.model.response.ResponseCandidate.ResponseAbilityProfile;
import com.example.springrestful.model.response.ResponseCandidate.ResponseCandidate;
import com.example.springrestful.model.response.ResponseCandidate.ResponseInfoApply;
import com.example.springrestful.model.response.ResponseRecruiter.ResponseRecruiter;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateService {
    ResponseCandidate saveCandidate(RequestCandidate requestCandidate) throws Exception;
    ResponseCandidate saveCandidatePartial (RequestPartialCandidate requestCandidate) throws Exception;
    void updateAvatarAndCVByEmail(String email, String avatarPath, String cvPath);
    ResponseCandidate removeCandidateById (int id) throws Exception;
    List<ResponseCandidate> findAllCandidates () throws Exception;
    ResponseCandidate findCandidateById (int id) throws Exception;
    ResponseCandidate editCandidate(RequestCandidate requestCandidate) throws Exception;
    void sendEmail(String to, String subject, String content) throws MessagingException;


    ResponseCandidate findByEmail(String email) throws Exception;
    List<ResponseCandidate> findByServiceContains(String service) throws Exception;
    List<ResponseCandidate> findByMajorContains(String major) throws Exception;


    ResponseAbilityProfile saveAbilityProfile (RequestAbilityProfile requestAbilityProfile) throws Exception;
    ResponseAbilityProfile removeAbilityProfileById (int id) throws Exception;
    List<ResponseAbilityProfile> findAllAbilityProfile () throws Exception;
    ResponseAbilityProfile findAbilityProfileById (int id) throws Exception;




    ResponseRecruiter followRecruiterById (int id) throws Exception;
    ResponseRecruiter cancelFollowRecruiterById (int id) throws Exception;
    void contactWithRecruiterById (int id) throws Exception;


    ResponseInfoApply applyJob (RequestInfoApply requestInfoApply) throws Exception;
    ResponseInfoApply cancelApplyJob (RequestInfoApply requestInfoApply) throws Exception;
    ResponseInfoApply findInfoApplyById (int id) throws Exception;

    String writeImg(MultipartFile file);

    String writeFile(MultipartFile file);

    RequestEvaluation evaluateRecruiterById (int id) throws Exception;



}
