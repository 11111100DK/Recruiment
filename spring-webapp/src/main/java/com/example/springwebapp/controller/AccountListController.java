package com.example.springwebapp.controller;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/15/2023
 * Time: 8:47 AM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccountRole;
import com.example.springwebapp.Service.AccountService.AccountService;
import com.example.springwebapp.Service.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class AccountListController {
    @Autowired
    AdminService adminService;

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/account/list")
    public String findAccountAll(Model model, @RequestParam(value = "role", required = false) String role) {
        List<ResponseAccount> accountList = null;
        List<ResponseAccountRole> accountRoleList =accountService.findAllRole() ;

        if (role == null) {
            accountList = accountService.findAllAccounts();
        } else {
            accountList = accountService.findByRole(Integer.parseInt(role) );
        }
        model.addAttribute("accountRoleList", accountRoleList);
        model.addAttribute("accountList", accountList);
        return "account_list";
    }
}
