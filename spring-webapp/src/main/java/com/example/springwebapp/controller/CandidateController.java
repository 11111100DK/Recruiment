package com.example.springwebapp.controller;

import com.example.springwebapp.Service.AdminService.AdminService;
import com.example.springwebapp.Service.NewsService.CandidateService;
import com.example.springwebapp.model.Static.User;
import com.example.springwebapp.model.request.RequestCandidate.RequestCandidate;
import com.example.springwebapp.model.request.RequestCandidate.RequestInfoApply;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ApiResponse.StatusEnum;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseCandidate.ResponseCandidate;
import com.example.springwebapp.model.response.ResponseRecruiter.ResponseRecruiter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class CandidateController {
    private static final String UPLOAD_DIRECTORY = "D://A//mock_project_final//spring-webapp//src//main//resources//static//candidate";

    @Autowired
    private CandidateService candidateService;
    @Autowired
    AdminService adminService;


    @GetMapping("/applyForm")
    public String showApplyJobForm(Model model){
        model.addAttribute("applyJob", new RequestInfoApply());
        model.addAttribute("username", User.name);
        model.addAttribute("role", User.role);
        return "applyForm";
    }
    @GetMapping("/ManageAccount")
    public String showManageAccountPage(Model model) throws Exception {
        ApiResponse<ResponseAccount> apiResponse = adminService.getAccountByUserName(User.name);
        model.addAttribute("account", apiResponse.getPayload());
        model.addAttribute("ManageAccount", new RequestCandidate());
        model.addAttribute("username", User.name);
        model.addAttribute("role", User.role);
        return "ManageAccount";
    }
    @GetMapping("/profileUser")
    public String showProfilePage(Model model) throws Exception {
//        var candidate = candidateService.getCandidateById(7);
//        model.addAttribute("candidate", candidate.get(0));
//        var can = new RequestCandidate();
//        can.setAvata("231028021849-Screenshot 2023-08-23 225744.png");
//        can.setCv("231028021849-Screenshot 2023-10-02 170147.png");

        ApiResponse<ResponseAccount> apiResponse = adminService.getAccountByUserName(User.name);
        model.addAttribute("candidate", new RequestCandidate());
        model.addAttribute("account", apiResponse.getPayload());
        model.addAttribute("username", User.name);
        model.addAttribute("role", User.role);
        return "profileUser1";
    }
    @PostMapping(value = "/profileUser")
        public String postProfile (Model model, @ModelAttribute("candidate") @Valid RequestCandidate requestCandidate, BindingResult bindingResult) throws Exception{
        model.addAttribute("username", User.name);
        model.addAttribute("role", User.role);

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "profileUser1"; // render view again
        }
        requestCandidate.setId(1);
        ApiResponse<ResponseCandidate> apiResponse = candidateService.saveCandidates(requestCandidate);
        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
            return "profileUser1";
        }

        model.addAttribute("successMessage", "Update profile successfully");
        return "profileUser1";
    }
    @GetMapping("/companiesApplied")
    public String showAppliedListPage(Model model){
        model.addAttribute("username", User.name);
        model.addAttribute("role", User.role);
        model.addAttribute("appliedList", new RequestInfoApply());
        return "companiesApplied";
    }

//    @PostMapping("/applyJob")
//    public String submitApplication(Model model,@ModelAttribute("infoApply")@Valid RequestInfoApply requestInfoApply, BindingResult bindingResult) throws Exception {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("errors", bindingResult.getAllErrors());
//            return "profileUser1"; // render view again
//        }
//        requestInfoApply.setId(1);
//        ApiResponse<ResponseCandidate> apiResponse = candidateService.save(requestInfoApply);
//        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
//            return "profileUser1";
//        }
//
//        model.addAttribute("successMessage", "Update profile successfully");
//        return "profileUser1";
//
//    }
//    @Autowired
//    private ResponseRecruiter companyRepo;
//
//    @GetMapping("/companies-applied")
//    public String showCompanies(Model model) {
//        List<Recrui> companies = companyRepo.findAll();
//        model.addAttribute("companies", companies);
//        return "companiesApplied";
//    }

}
