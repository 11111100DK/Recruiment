package com.example.springwebapp.controller;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/8/2023
 * Time: 2:54 PM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springwebapp.model.request.RequestAdmin.RequestAdmin;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ApiResponse.StatusEnum;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springwebapp.Service.AccountService.AccountService;
import com.example.springwebapp.Service.AdminService.AdminService;
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
public class AdminFormController {
    @Autowired
    AccountService accountService;

    @Autowired
    AdminService adminService;
    @GetMapping(value = "/admin/form")
    public String newObject(Model model, @RequestParam(value = "action",required = false) String action) {
        RequestAdmin requestAdmin =new RequestAdmin();
        if (!action.equalsIgnoreCase("new")){

            return "redirect:/admin/list";
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
        return "admin_form";
    }
    @GetMapping(value = "/admin/form/{id}")
    public String findAll(Model model, @PathVariable(value = "id", required = false) int id) {
        ResponseAdmin responseAdmin = adminService.findById(id);
        if (responseAdmin == null) {
            return "redirect:/admin/list";
        }

        List<ResponseAccount> accountList = accountService.findAllAccounts();

        model.addAttribute("accountList", accountList);
        model.addAttribute("responseAdmin", responseAdmin);
        model.addAttribute("errorMap", new LinkedHashMap<>());
        return "admin_form";
    }

    @PostMapping (value = "/admin/form")
    public String submit(Model model, @ModelAttribute("responseAdmin") @Valid RequestAdmin requestAdmin, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            List<CategoryResponse> categoryList = categoryService.findAll();
//
//            model.addAttribute("categoryList", categoryList);
//            model.addAttribute("product", product);
//            return "product_form";
//        }

//        product.setPrice(product.getPrice().replaceAll(",", ""));
        ApiResponse<ResponseAdmin> apiResponse = adminService.save(requestAdmin);

        if (apiResponse != null && apiResponse.getStatus() == StatusEnum.SUCCESS) {
            return "redirect:/admin/list";
        }

        LinkedHashMap<String, String> errorMap = (LinkedHashMap) apiResponse.getMessage();
        List<ResponseAccount> accountList = accountService.findAllAccounts();

        model.addAttribute("accountList", accountList);
        model.addAttribute("requestAdmin", requestAdmin);
        model.addAttribute("messageError", "An error has occurred, please accept it again!");
        model.addAttribute("errorMap", errorMap);
        return "admin_form";
    }
    @DeleteMapping(value = "/admin/delete")
    @ResponseBody
    public ResponseEntity<String> adminDelete(@RequestParam(value = "id", required = false) int adminId) {
        ResponseAdmin responseAdmin = adminService.findById(adminId);
        if (responseAdmin == null) {
            return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        responseAdmin.setStatus(0);

        RequestAdmin requestAdmin= new RequestAdmin();
        requestAdmin.setId(responseAdmin.getId());
        requestAdmin.setStatus(responseAdmin.getStatus());
        requestAdmin.setMajor(responseAdmin.getMajor());
        requestAdmin.setDescription(responseAdmin.getDescription());
        requestAdmin.setRole(responseAdmin.getRole());
        requestAdmin.setAccountId(responseAdmin.getAccountId());
        requestAdmin.setExperienceYear(responseAdmin.getExperienceYear());

        adminService.save(requestAdmin);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }
}
