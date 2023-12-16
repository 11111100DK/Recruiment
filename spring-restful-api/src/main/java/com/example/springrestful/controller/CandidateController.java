package com.example.springrestful.controller;

import com.example.springrestful.model.request.RequestCandidate.*;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import com.example.springrestful.model.response.ApiResponse.StatusEnum;
import com.example.springrestful.model.response.ResponseCandidate.ResponseAbilityProfile;
import com.example.springrestful.model.response.ResponseCandidate.ResponseCandidate;
import com.example.springrestful.model.response.ResponseCandidate.ResponseInfoApply;
import com.example.springrestful.service.CandidateService.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping(value = "/saveCandidates")
    public ResponseEntity<ResponseCandidate> saveCandidate( @RequestBody RequestPartialCandidate requestCandidate ) throws Exception {
        ApiResponse apiResponse = new ApiResponse();

        if (requestCandidate == null) {
            apiResponse.setMessage("Request candidate is null");
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }

        ResponseCandidate responseCandidate = candidateService.saveCandidatePartial(requestCandidate);
        apiResponse.ok("");
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/saveCandidateFile", consumes =MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<ResponseCandidate> saveCandidateFile(@RequestParam("avata") MultipartFile avatar,
                                                               @RequestParam("cv") MultipartFile cv,
                                                               @RequestParam("email") String email) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            StringBuilder filepathCV = new StringBuilder("D:\\A\\mock_project_final\\spring-webapp\\src\\main\\resources\\static\\images\\candidate\\cv\\");
            StringBuilder filepathAvatar = new StringBuilder("D:\\A\\mock_project_final\\spring-webapp\\src\\main\\resources\\static\\images\\candidate\\avatar\\");
            String avatarFileName = avatar.getOriginalFilename();
            String cvFileName = cv.getOriginalFilename();

            filepathCV.append(cvFileName);
            String cvPath = filepathCV.toString();

            filepathAvatar.append(avatarFileName);
            String avatarPath = filepathAvatar.toString();

            // Save avatar file
            avatar.transferTo(new File(avatarPath));

            // Save CV file
            cv.transferTo(new File(cvPath));
            candidateService.updateAvatarAndCVByEmail(email, avatarPath, cvPath);

            // Perform other operations with the email parameter if needed
            apiResponse.setMessage("Candidate files uploaded successfully.");
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/removeCandidates/{id}")
    public ResponseEntity<Void> removeCandidateById(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();

        if (candidateService.findCandidateById(id) == null) {
            apiResponse.setMessage("ID candidate have not int database");
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
        }
        ResponseCandidate responseCandidate = candidateService.removeCandidateById(id);
        apiResponse.ok();
        return new ResponseEntity(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/findAllCandidates/list")
    public ResponseEntity<List<ResponseCandidate>> findAllCandidates() {
        try {
            List<ResponseCandidate> candidates = candidateService.findAllCandidates();
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(candidates);
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findCandidates/{id}")
    public ResponseEntity<ResponseCandidate> findCandidateById(@PathVariable int id) {
        try {
            ResponseCandidate candidate = candidateService.findCandidateById(id);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok();
            return new ResponseEntity(apiResponse,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/editCandidate/{id}")
    public ResponseEntity<ResponseCandidate> editCandidate(@RequestBody RequestCandidate requestCandidate) throws Exception{
        ApiResponse apiResponse = new ApiResponse();
        if (requestCandidate == null) {
            apiResponse.setMessage("BAD_REQUEST");
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
        ResponseCandidate responseCandidate = candidateService.editCandidate(requestCandidate);
        apiResponse.ok();
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/saveAbilityProfiles")
    public ResponseEntity<ResponseAbilityProfile> saveAbilityProfile(@RequestBody RequestAbilityProfile requestAbilityProfile) throws Exception {
        ApiResponse apiResponse =  new ApiResponse();
        if (requestAbilityProfile == null){
            apiResponse.setMessage("error");
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
        ResponseAbilityProfile responseAbilityProfile = candidateService.saveAbilityProfile(requestAbilityProfile);
        apiResponse.ok();
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/removeAbilityProfiles/{id}")
    public ResponseEntity<Void> removeAbilityProfileById(@PathVariable int id) throws Exception{
        ApiResponse apiResponse = new ApiResponse();
        if (candidateService.findAbilityProfileById(id) == null){
            apiResponse.setMessage("Id is not found");
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
        }
        ResponseAbilityProfile responseAbilityProfile = candidateService.removeAbilityProfileById(id);
        apiResponse.ok();
        return new ResponseEntity(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/findAllAbilityProfiles")
    public ResponseEntity<List<ResponseAbilityProfile>> findAllAbilityProfiles() throws Exception {
        try {
            List<ResponseAbilityProfile> abilityProfiles = candidateService.findAllAbilityProfile();
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(abilityProfiles);
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/findAbilityProfiles/{id}")
    public ResponseEntity<ResponseAbilityProfile> findAbilityProfileById(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        if(candidateService.findAbilityProfileById(id) == null){
            apiResponse.setMessage("id is not found");
            apiResponse.setStatus(StatusEnum.ERROR);
            return  new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
        }
        ResponseAbilityProfile responseAbilityProfile = candidateService.findAbilityProfileById(id);
        apiResponse.ok(responseAbilityProfile);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/applyJob")
    public ResponseEntity<ResponseInfoApply> apply(@RequestBody RequestInfoApply requestInfoApply) throws Exception{

        ApiResponse apiResponse = new ApiResponse();
        if (requestInfoApply == null){
            apiResponse.setMessage("Request info apply is null");
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }

        ResponseInfoApply responseInfoApply = candidateService.applyJob(requestInfoApply);
        apiResponse.ok(responseInfoApply);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/cancelApply")
    public ResponseEntity<Void> cancelApply(@RequestBody RequestInfoApply requestInfoApply) throws Exception{
        ApiResponse apiResponse = new ApiResponse();
        if (candidateService.findInfoApplyById(requestInfoApply.getId())== null){
            apiResponse.setMessage("Request info apply ID is null");
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
        }

        ResponseInfoApply responseInfoApply = candidateService.cancelApplyJob(requestInfoApply);
        apiResponse.ok(responseInfoApply);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getProfile/{id}")
    public ResponseEntity<List<ResponseCandidate>> getProfile(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        var candidate = candidateService.findCandidateById(id);
        if (candidate == null){
            apiResponse.setMessage("Request info apply ID is null");
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
        }

        var listCandidate = new ArrayList<ResponseCandidate>();
        listCandidate.add(candidate);
        apiResponse.ok(listCandidate);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/search/major")
    public ResponseEntity<ResponseCandidate> searchByMajor(@RequestParam String major) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        if(candidateService.findByMajorContains(major) == null){
            apiResponse.setMessage("major is not found");
            apiResponse.setStatus(StatusEnum.ERROR);
            return  new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
        }
        List<ResponseCandidate> responseCandidate = candidateService.findByMajorContains(major);
        if(responseCandidate.isEmpty()){
            apiResponse.setStatus(StatusEnum.ERROR);
            apiResponse.setMessage("Your key is not found in Data .");
            return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
        }
        apiResponse.ok(responseCandidate);
        return new ResponseEntity(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/search/services")
    public ResponseEntity<List<ResponseCandidate>> searchByServices(@RequestParam String services) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        if(candidateService.findByServiceContains(services) == null){
            apiResponse.setMessage("service is not found");
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<ResponseCandidate> responseCandidate = candidateService.findByServiceContains(services);
        if(responseCandidate.isEmpty()){
            apiResponse.setStatus(StatusEnum.ERROR);
            apiResponse.setMessage("Your key is not found in Data .");
            return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
        }
        apiResponse.ok(responseCandidate);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }
}
