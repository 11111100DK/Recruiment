package com.example.springwebapp.controller;

import com.example.springwebapp.Service.AdminService.AdminService;
import com.example.springwebapp.model.Static.Admin;
import com.example.springwebapp.model.Static.User;
import com.example.springwebapp.model.request.RequestAccount.RequestAccountRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {
    @Autowired
    AdminService adminService;
    @GetMapping(value = "/")
    public String getHomeView (Model model) {
        adminService.increaseAccessWebsite();
        model.addAttribute("username", User.name);
        model.addAttribute("role", User.role);
        return "index";
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody RequestAccountRegister requestAccountRegister) throws Exception {
        adminService.createAdmin(requestAccountRegister);
        return ResponseEntity.ok("success");
    }

    @GetMapping(value = "/logout")
    public String logout () {
        User.id = -1;
        User.role = "";
        User.name = null;
        Admin.id = -1;
        Admin.userName = "";
        Admin.nameRole = "";
        Admin.account_manage = 0;
        Admin.admin_manage = 0;
        Admin.role_manage = 0;
        return "redirect:/";
    }

}
