package com.example.springwebapp.controller;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/16/2023
 * Time: 7:24 PM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springwebapp.Service.AccountService.AccountService;
import com.example.springwebapp.model.request.RequestAccount.RequestAccountEdit;
import com.example.springwebapp.model.request.RequestAccount.RequestAccountRegister;
import com.example.springwebapp.model.request.RequestAdmin.RequestAdmin;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ApiResponse.StatusEnum;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class AccountFormController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/account/form")
    public String newObject(Model model, @RequestParam(value = "action", required = false) String action, @RequestParam(value = "id", required = false) int id) {
        RequestAdmin requestAdmin = new RequestAdmin();

        if (!action.equalsIgnoreCase("new")) {

            ResponseAccount responseAccount = accountService.findById(id);
            if (responseAccount == null) {
                return "redirect:/account/list";
            }
            List<ResponseAccount> accountList = accountService.findAllAccounts();

            model.addAttribute("accountList", accountList);
            model.addAttribute("responseAccount", responseAccount);
            model.addAttribute("errorMap", new LinkedHashMap<>());
            return "account_form";
        }

        requestAdmin.setId(0);
        requestAdmin.setDescription("");
        requestAdmin.setMajor("");
        requestAdmin.setRole("");
        requestAdmin.setStatus(0);
        requestAdmin.setExperienceYear(0);
        requestAdmin.setAccountId(0);

//        model.addAttribute("accountList", accountList);
        model.addAttribute("responseAdmin", requestAdmin);
        model.addAttribute("errorMap", new LinkedHashMap<>());
        return "account_form";
    }

//    @GetMapping(value = "/account/form/{id}")
//    public String findAll(Model model, @PathVariable(value = "id", required = false) int id) {
//        ResponseAccount responseAccount = accountService.findById(id);
//        if (responseAccount == null) {
//            return "redirect:/account/list";
//        }
//
//        List<ResponseAccount> accountList = accountService.findAllAccounts();
//
//        model.addAttribute("accountList", accountList);
//        model.addAttribute("responseAccount", responseAccount);
//        model.addAttribute("errorMap", new LinkedHashMap<>());
//        return "account_form";
//    }

    @PostMapping(value = "/account/form")
    public String submit(Model model, @RequestParam(value = "action", required = false) String action,
                         @ModelAttribute("responseAccount") @Valid RequestAccountRegister requestAccountRegister,
                          BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            List<CategoryResponse> categoryList = categoryService.findAll();
//
//            model.addAttribute("categoryList", categoryList);
//            model.addAttribute("product", product);
//            return "product_form";
//        }

//        product.setPrice(product.getPrice().replaceAll(",", ""));

        ApiResponse<ResponseAccount> apiResponse = accountService.save(requestAccountRegister);
        if (apiResponse != null && apiResponse.getStatus() == StatusEnum.SUCCESS) {
            return "redirect:/account/list";
        }
        LinkedHashMap<String, String> errorMap = (LinkedHashMap) apiResponse.getMessage();
        model.addAttribute("errorMap", errorMap);
        List<ResponseAccount> accountList = accountService.findAllAccounts();

        model.addAttribute("accountList", accountList);
        model.addAttribute("requestAccount", requestAccountRegister);
        model.addAttribute("messageError", "An error has occurred, please accept it again!");

        return "account_form";
    }

    @PostMapping(value = "/account/form?action=edit")
    public String update(Model model, @RequestParam(value = "action", required = false) String action,
                         @ModelAttribute("responseAccount") @Valid RequestAccountEdit requestAccountEdit,
                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            List<CategoryResponse> categoryList = categoryService.findAll();
//
//            model.addAttribute("categoryList", categoryList);
//            model.addAttribute("product", product);
//            return "product_form";
//        }

//        product.setPrice(product.getPrice().replaceAll(",", ""));

        ApiResponse<ResponseAccount> apiResponse = accountService.save(requestAccountEdit);
        if (apiResponse != null && apiResponse.getStatus() == StatusEnum.SUCCESS) {
            return "redirect:/account/list";
        }
        LinkedHashMap<String, String> errorMap = (LinkedHashMap) apiResponse.getMessage();
        model.addAttribute("errorMap", errorMap);
        List<ResponseAccount> accountList = accountService.findAllAccounts();

        model.addAttribute("accountList", accountList);
        model.addAttribute("requestAccount", requestAccountEdit);
        model.addAttribute("messageError", "An error has occurred, please accept it again!");

        return "account_form";
    }
    @DeleteMapping(value = "/account/delete")
    @ResponseBody
    public ResponseEntity<String> accountDelete(@RequestParam(value = "id", required = false) int accountId) {
        ResponseAccount responseAccount = accountService.findById(accountId);
        if (responseAccount == null) {
            return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        responseAccount.setStatus(0);

//        RequestAccountEdit requestAccount= new RequestAccountEdit();
//        requestAccount.setId(Integer.parseInt(responseAccount.getId()) );
//        requestAccount.setUsername();
//        requestAccount.setRole();
//        requestAccount.set;
//        requestAccount.setRole();
//        requestAccount.setAccountId();
//        requestAccount.setExperienceYear();

        accountService.update(responseAccount);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }
}
