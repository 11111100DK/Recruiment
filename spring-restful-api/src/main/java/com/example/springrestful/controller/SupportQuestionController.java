package com.example.springrestful.controller;

import com.example.springrestful.exception.NotFoundException;
import com.example.springrestful.model.entity.SupportQuestion.SupportQuestion;
import com.example.springrestful.model.mapper.SupportQuestionMapper;
import com.example.springrestful.model.request.RequestSupportQuestion.RequestSupportQuestion;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import com.example.springrestful.model.response.ResponseSupportQuestion.ResponseSupportQuestion;
import com.example.springrestful.service.SupportAndContactService.SupportAndContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support-questions")
public class SupportQuestionController {

    @Autowired
    SupportAndContactService supportAndContactService;

    @Autowired
    SupportQuestionMapper supportQuestionMapper;

    @GetMapping(value = {""})
    public ResponseEntity<ApiResponse<List<ResponseSupportQuestion>>> getAllSupportQuestions(
            @RequestParam(value = "account", required = false) String account) {
        List<SupportQuestion> supportQuestionList = null;
        if (account == null) {
            supportQuestionList = supportAndContactService.findAll();
        } else {
            supportQuestionList = supportAndContactService.findByAccount(Integer.parseInt(account));
        }
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(supportQuestionMapper.toResponseSupportQuestionList(supportQuestionList));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/id")
    public ResponseEntity<ApiResponse<SupportQuestion>> getByIdRequestParam(@RequestParam int id) {
        ApiResponse apiResponse = new ApiResponse();
        SupportQuestion supportQuestion = supportAndContactService.findById(id);
        if (supportQuestion == null) {
            throw new NotFoundException("Support Question Not Found");
        }
        // Response
        apiResponse.ok(supportQuestionMapper.toResponseSupportQuestion(supportQuestion));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = {""})
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody RequestSupportQuestion requestSupportQuestion, BindingResult bindingResult) {
        // Save
        ResponseSupportQuestion responseSupportQuestion = supportAndContactService.saveQuestion(requestSupportQuestion, bindingResult);

        // Response
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(responseSupportQuestion);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(value = {""})
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody RequestSupportQuestion requestSupportQuestion, BindingResult bindingResult) {
        ResponseSupportQuestion responseSupportQuestion = supportAndContactService.updateQuestion(requestSupportQuestion, bindingResult);
        // Response
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(responseSupportQuestion);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<ApiResponse> delete(@PathVariable int id) {
        // Delete
        supportAndContactService.deleteQuestionById(id);

        // Response
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok();
        return ResponseEntity.ok(apiResponse);
    }
}
