package com.example.springrestful.service.CandidateService;

import com.example.springrestful.exception.ExistEmailException;
import com.example.springrestful.exception.NotFoundException;
import com.example.springrestful.model.entity.Candidate.AbilityProfile;
import com.example.springrestful.model.entity.Candidate.Candidate;
import com.example.springrestful.model.entity.Candidate.InfoApply;
import com.example.springrestful.model.mapper.AbilityProfileMapper;
import com.example.springrestful.model.mapper.CandidateMapper;
import com.example.springrestful.model.mapper.InfoApplyMapper;
import com.example.springrestful.model.request.RequestCandidate.RequestAbilityProfile;
import com.example.springrestful.model.request.RequestCandidate.RequestCandidate;
import com.example.springrestful.model.request.RequestCandidate.RequestInfoApply;
import com.example.springrestful.model.request.RequestCandidate.RequestPartialCandidate;
import com.example.springrestful.model.request.RequestRecruiter.RequestEvaluation;
import com.example.springrestful.model.response.ResponseCandidate.ResponseAbilityProfile;
import com.example.springrestful.model.response.ResponseCandidate.ResponseCandidate;
import com.example.springrestful.model.response.ResponseCandidate.ResponseInfoApply;
import com.example.springrestful.model.response.ResponseRecruiter.ResponseRecruiter;
import com.example.springrestful.repository.AbilityProfileRepository;
import com.example.springrestful.repository.CandidateRepository;
import com.example.springrestful.repository.InfoApplyRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService{
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    CandidateMapper candidateMapper;

    @Autowired
    AbilityProfileMapper abilityProfileMapper;
    @Autowired
    AbilityProfileRepository abilityProfileRepository;

//    @Autowired
//    JobRepository jobRepository;

    @Autowired
    InfoApplyRepository infoApplyRepository;
    @Autowired
    InfoApplyMapper infoApplyMapper;

    @Autowired
    private JavaMailSender mailSender;


    public String writeImg(MultipartFile file) {
        try {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
            String fileName = date + file.getOriginalFilename();

            String filePath = "D:\\A\\mock_project_final\\spring-webapp\\src\\main\\resources\\static\\images\\candidate\\avatar\\" + fileName;

            // Copies Spring's multipartfile inputStream to /sismed/temp/exames (absolute path)
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception ex) {
            return null;
        }
    }
    public String writeFile(MultipartFile file) {
        try{
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
            String fileName = date + file.getOriginalFilename();

            String filePath = "D:\\A\\mock_project_final\\spring-webapp\\src\\main\\resources\\static\\images\\candidate\\cv\\" + fileName;
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        }catch (Exception ex){
            return  null;
        }
    }

    @Override
    public ResponseCandidate saveCandidate(RequestCandidate requestCandidate) throws Exception {
        String pathAvata = writeImg(requestCandidate.getAvata());
        String pathCv = writeFile(requestCandidate.getAvata());


        Candidate saveCandidate = new Candidate();
        String email = requestCandidate.getEmail();
        Candidate chkEmail = candidateRepository.findByEmail(email);
        if(chkEmail == null){
            saveCandidate.setFullName(requestCandidate.getFullName());
            saveCandidate.setNickname(requestCandidate.getNickname());
            saveCandidate.setDescription(requestCandidate.getDescription());
//            saveCandidate.setLinkWebsite(requestCandidate.getLinkWebsite());
            saveCandidate.setExperienceYear(requestCandidate.getExperienceYear());
            saveCandidate.setMajor(requestCandidate.getMajor());
            saveCandidate.setService(requestCandidate.getService());
            saveCandidate.setCountry(requestCandidate.getCountry());
            saveCandidate.setAddress(requestCandidate.getAddress());
            saveCandidate.setAvata(pathAvata);
            saveCandidate.setCv(pathCv);
            saveCandidate.setEmail(requestCandidate.getEmail());
            candidateRepository.save(saveCandidate);
        }else {
            throw new ExistEmailException("Email already exist.");
        }
        return candidateMapper.toResponseCandidate(candidateRepository.save(saveCandidate));
    }

    @Override
    public ResponseCandidate saveCandidatePartial(RequestPartialCandidate requestCandidate) throws Exception {

        Candidate saveCandidate = new Candidate();
        String email = requestCandidate.getEmail();
        Candidate chkEmail = candidateRepository.findByEmail(email);
        if(chkEmail == null){
            saveCandidate.setFullName(requestCandidate.getFullName());
            saveCandidate.setNickname(requestCandidate.getNickname());
            saveCandidate.setDescription(requestCandidate.getDescription());
//            saveCandidate.setLinkWebsite(requestCandidate.getLinkWebsite());
            saveCandidate.setExperienceYear(requestCandidate.getExperienceYear());
            saveCandidate.setMajor(requestCandidate.getMajor());
            saveCandidate.setService(requestCandidate.getService());
            saveCandidate.setCountry(requestCandidate.getCountry());
            saveCandidate.setAddress(requestCandidate.getAddress());
            saveCandidate.setEmail(requestCandidate.getEmail());
            saveCandidate.setPhoneNumber(requestCandidate.getPhoneNumber());
            saveCandidate.setAvata("default");
            saveCandidate.setCv("default");
            candidateRepository.save(saveCandidate);
        }else {
            throw new ExistEmailException("Email already exist.");
        }
        return candidateMapper.toResponseCandidate(candidateRepository.save(saveCandidate));
    }


    @Override
    public void updateAvatarAndCVByEmail(String email, String avatarPath, String cvPath) {
        Candidate can = candidateRepository.findByEmail(email);
        if(can != null){
            can.setCv(cvPath);
            can.setAvata(avatarPath);
        }

        candidateRepository.save(can);
    }

    @Override
    public ResponseCandidate removeCandidateById(int id) throws Exception {
        candidateRepository.deleteById(id);
        return null;
    }

    @Override
    public List<ResponseCandidate> findAllCandidates() throws Exception {
        return candidateMapper.toResponseCandidateList(candidateRepository.findAll());
    }

    @Override
    public ResponseCandidate findCandidateById(int id) throws Exception {
        var c = candidateRepository.findCandidateById(id);
        return candidateMapper.toResponseCandidate(c);
    }

    @Override
    public ResponseCandidate findByEmail(String email) throws Exception {
        return candidateMapper.toResponseCandidate(candidateRepository.findByEmail(email));
    }

    @Override
    public List<ResponseCandidate> findByServiceContains(String service) throws Exception{
        return candidateMapper.toResponseCandidateList(candidateRepository.findByServiceContains(service));
    }

    @Override
    public ResponseAbilityProfile saveAbilityProfile(RequestAbilityProfile requestAbilityProfile) throws Exception {
        AbilityProfile saveAbilityProfile = new AbilityProfile();
        saveAbilityProfile.setTitle(requestAbilityProfile.getTitle());
        saveAbilityProfile.setProjectLink(requestAbilityProfile.getProjectLink());
        saveAbilityProfile.setDateCreated(requestAbilityProfile.getDateCreated());
        saveAbilityProfile.setDateUpdated(requestAbilityProfile.getDateUpdated());
        return abilityProfileMapper.toResponseAbilityProfile(abilityProfileRepository.save(saveAbilityProfile));
    }

    @Override
    public ResponseAbilityProfile removeAbilityProfileById(int id) throws Exception {
        abilityProfileRepository.deleteById(id);
        return null;
    }

    @Override
    public List<ResponseAbilityProfile> findAllAbilityProfile() throws Exception {
        return abilityProfileMapper.toResponseAbilityProfileList(abilityProfileRepository.findAll());
    }

    @Override
    public ResponseAbilityProfile findAbilityProfileById(int id) throws Exception {
        return abilityProfileMapper.toResponseAbilityProfile(abilityProfileRepository.findById(id));
    }

    @Override
    public ResponseRecruiter followRecruiterById(int id) throws Exception {
        return null;
    }

    @Override
    public ResponseRecruiter cancelFollowRecruiterById(int id) throws Exception {
        return null;
    }

    @Override
    public void contactWithRecruiterById(int id) throws Exception {

    }

    @Override
    public ResponseInfoApply applyJob(RequestInfoApply requestInfoApply) throws Exception {

//        validate input
        if(requestInfoApply ==null){
            throw new ValidationException("BAD_REQUEST");
        }
        InfoApply infoApply = infoApplyRepository.findByCandidateId(requestInfoApply.getCandidateId());
        if(infoApply == null) {
            infoApply = new InfoApply();
        }

        infoApply.setAppliedDate(new Date());
        infoApply.setStatus(1);
        infoApplyRepository.save(infoApply);

        Candidate candidate = candidateRepository.findCandidateById(requestInfoApply.getCandidateId());

        sendEmail(candidate.getEmail(), "Xác nhận email", "Mail đã được gửi thành công");

        return infoApplyMapper.toResponseInfoApply(infoApplyRepository.save(infoApply));
    }

    public void sendEmail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);

        mailSender.send(message);
    }

    @Override
    public ResponseInfoApply cancelApplyJob(RequestInfoApply requestInfoApply) throws Exception {
        //        validate input
        if(requestInfoApply ==null){
            throw new ValidationException("BAD_REQUEST");
        }
        InfoApply infoApply = infoApplyRepository.findByCandidateId(requestInfoApply.getCandidateId());
        if(infoApply == null ){
            throw new NotFoundException("Info Apply Not found");
        }
        infoApply.setStatus(0);
        infoApplyRepository.save(infoApply);

        Candidate candidate = candidateRepository.findCandidateById(requestInfoApply.getCandidateId());

        sendEmail(candidate.getEmail(), "Xác nhận email", "Mail đã được hủy thành công");

        return infoApplyMapper.toResponseInfoApply(infoApplyRepository.save(infoApply));
    }

    @Override
    public ResponseInfoApply findInfoApplyById(int id) throws Exception {
        return infoApplyMapper.toResponseInfoApply(infoApplyRepository.findById(id));
    }

    @Override
    public RequestEvaluation evaluateRecruiterById(int id) throws Exception {
        return null;
    }

    @Override
    public List<ResponseCandidate> findByMajorContains(String major) throws Exception{
        return  candidateMapper.toResponseCandidateList(candidateRepository.findByMajorContains(major)) ;
    }

    @Override
    public ResponseCandidate editCandidate(RequestCandidate requestCandidate) throws Exception {
        Candidate editCandidate = new Candidate();
        int id = requestCandidate.getId();
        if (candidateRepository.findById(id) != null) {

            editCandidate.setFullName(requestCandidate.getFullName());
            editCandidate.setNickname(requestCandidate.getNickname());
            editCandidate.setDescription(requestCandidate.getDescription());
//            editCandidate.setLinkWebsite(requestCandidate.getLinkWebsite());
            editCandidate.setExperienceYear(requestCandidate.getExperienceYear());
            editCandidate.setMajor(requestCandidate.getMajor());
            editCandidate.setService(requestCandidate.getService());
            candidateRepository.save(editCandidate);
        } else {
            throw new NotFoundException("Candidate not found in data.");
        }
        return candidateMapper.toResponseCandidate(candidateRepository.save(editCandidate));
    }
}

