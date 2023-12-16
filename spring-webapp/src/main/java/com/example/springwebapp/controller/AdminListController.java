package com.example.springwebapp.controller;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/6/2023
 * Time: 7:11 PM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springwebapp.model.Mapper.AccountMapper;
import com.example.springwebapp.model.Mapper.JobMapper;
import com.example.springwebapp.model.Static.User;
import com.example.springwebapp.model.model.Pager;
import com.example.springwebapp.model.request.RequestAccount.RequestAccountLogin;
import com.example.springwebapp.model.request.RequestAccount.RequestAccountRegister;
import com.example.springwebapp.model.request.RequestChangeStatus.ResquestChangeStatus;
import com.example.springwebapp.model.request.RequestRole.RequestRole;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ApiResponse.StatusEnum;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springwebapp.Service.AccountService.AccountService;
import com.example.springwebapp.Service.AdminService.AdminService;
import com.example.springwebapp.model.response.ResponseDashboard.ResponseDashboard;
import com.example.springwebapp.model.response.ResponseRole.ResponseRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

import com.example.springwebapp.model.Static.Admin;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminListController {
    @Autowired
    AdminService adminService;

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/admin/login")
    public String getLoginView() {
        return "/admin/login";
    }

    @PostMapping(value = "/admin/login")
    public String checkLoginView(@ModelAttribute("requestAccountLogin") RequestAccountLogin requestAccountLogin, Model model) throws Exception {
        requestAccountLogin.setIsAdmin(1);
        ResponseAccount account = adminService.loginAccount(requestAccountLogin);
        if (account == null) {
            model.addAttribute("mess", "login fail");
            return "/admin/login";
        }else if (account.getStatus() == 1) {
            model.addAttribute("mess", "blocked");
            return "/admin/login";
        } else {
            if (account.getRole().equals("Admin")) {
                Admin.userName = account.getUsername();
                Admin.nameRole = account.getRole_id().getName();
                Admin.account_manage = account.getRole_id().getAccount_manage();
                Admin.admin_manage = account.getRole_id().getAdmin_manage();
                Admin.role_manage = account.getRole_id().getRole_manage();
                Admin.id = account.getId();
                User.name = account.getUsername();
                User.role = account.getRole();
                User.id = account.getId();
                model.addAttribute("admin", account);
                return "redirect:/admin/index";
            } else {
                model.addAttribute("mess", "Is not Admin");
                return "/admin/login";
            }
        }
    }

    @GetMapping(value = "/admin/index")
    public String getIndexView(Model model) {
        if (Admin.userName.isEmpty()) return "redirect:/admin/login";

        ResponseDashboard res = adminService.countStatistics();
        model.addAttribute("jobs", res.getCountJobs());
        model.addAttribute("user", res.getCountUser());
        model.addAttribute("recruiters", res.getCountRecruiters());
        model.addAttribute("users", res.getCountUsers());

        model.addAttribute("username", username());
        model.addAttribute("roleName", role());
        return "/admin/index";
    }

    @GetMapping(value = "/admin/account")
    public String getAccountView() {

        return "/admin/account";
    }

    @GetMapping(value = "/admin/role")
    public String getRoleView(RedirectAttributes redirectAttributes, Model model, @RequestParam(value = "name", required = false) String name) throws Exception {
        if (Admin.userName.isEmpty()) return "redirect:/admin/login";
        if (Admin.role_manage == 0) {
            redirectAttributes.addFlashAttribute("mess", "Bạn không có quyền sử dụng chức năng này");
            return "redirect:/admin/index";
        }
        model.addAttribute("username", username());
        model.addAttribute("roleName", role());

        List<ResponseRole> apiResponse = adminService.getAllRole(name);
        model.addAttribute("name", name);
        model.addAttribute("listRole", apiResponse);
        return "/admin/role";
    }

    @GetMapping(value = "/admin/role/addRole")
    public String getAddRoleView(Model model) throws Exception {
        if (Admin.userName.isEmpty()) return "redirect:/admin/login";

        model.addAttribute("username", username());
        model.addAttribute("roleName", role());
        model.addAttribute("role", new RequestRole());
        return "/admin/addRole";
    }

    @PostMapping(value = "/admin/role/saveRole")
    public String postSaveRole(RedirectAttributes redirectAttributes, Model model, @ModelAttribute("role") RequestRole requestRole) throws Exception {
        if (requestRole.getName().isEmpty()) {
            redirectAttributes.addFlashAttribute("mess", "Name role is required");
            redirectAttributes.addFlashAttribute("role", requestRole);
            redirectAttributes.addFlashAttribute("username", username());
            redirectAttributes.addFlashAttribute("roleName", role());
            return "redirect:/admin/role/addRole";
        }

        ApiResponse<ResponseRole> apiResponse = adminService.addRole(requestRole);
        if (apiResponse.getStatus() == StatusEnum.ERROR) {
            redirectAttributes.addFlashAttribute("mess", "Name is exist");
            redirectAttributes.addFlashAttribute("username", username());
            redirectAttributes.addFlashAttribute("roleName", role());
            return "redirect:/admin/role/addRole";
        }

        redirectAttributes.addFlashAttribute("mess", "success");
        redirectAttributes.addFlashAttribute("username", username());
        redirectAttributes.addFlashAttribute("roleName", role());
        return "redirect:/admin/role";
    }

    @GetMapping(value = "/admin/role/detailRole/{id}")
    public String getDetailRoleView(Model model, @PathVariable int id) throws Exception {
        if (Admin.userName.isEmpty()) return "redirect:/admin/login";
        model.addAttribute("username", username());
        model.addAttribute("roleName", role());

        ApiResponse<ResponseRole> apiResponse = adminService.getRoleById(id);
        model.addAttribute("role", apiResponse.getPayload());
        return "/admin/detailRole";
    }

    @PostMapping(value = "/admin/role/editRole")
    public String getEditRoleView(RedirectAttributes redirectAttributes, Model model, @ModelAttribute("role") RequestRole requestRole) throws Exception {
        if (requestRole.getName().isEmpty()) {
            redirectAttributes.addFlashAttribute("mess", "Name role is required");
            redirectAttributes.addFlashAttribute("role", requestRole);
            redirectAttributes.addFlashAttribute("username", username());
            redirectAttributes.addFlashAttribute("roleName", role());
            return "redirect:/admin/role/detailRole/" + requestRole.getId();
        }
        ApiResponse<ResponseRole> apiResponse = adminService.editRole(requestRole);
        System.out.println(apiResponse);
        if (apiResponse.getStatus() == StatusEnum.ERROR) {
            redirectAttributes.addFlashAttribute("username", username());
            redirectAttributes.addFlashAttribute("roleName", role());
            redirectAttributes.addFlashAttribute("mess", "Name is exist");
            return "redirect:/admin/role/detailRole/" + requestRole.getId();
        }
        redirectAttributes.addFlashAttribute("mess", "success");
        redirectAttributes.addFlashAttribute("username", username());
        redirectAttributes.addFlashAttribute("roleName", role());
        return "redirect:/admin/role/detailRole/" + requestRole.getId();
    }

    @GetMapping(value = "/admin/role/delete/{id}")
    public String deleteRole(RedirectAttributes redirectAttributes, @PathVariable int id) throws Exception {
        ApiResponse<ResponseRole> apiResponse = adminService.deleteRoleById(id);
        if (apiResponse.getStatus() == StatusEnum.ERROR) {
            redirectAttributes.addFlashAttribute("mess", apiResponse.getMessage());
        } else {
            redirectAttributes.addFlashAttribute("mess", "success");
        }
        redirectAttributes.addFlashAttribute("username", username());
        redirectAttributes.addFlashAttribute("roleName", role());
        return "redirect:/admin/role";
    }

    //+++++++++++++++++++++++++++User+++++++++++++++++++++++++++++++++++++++
    @GetMapping(value = "/admin/accountUser")
    public String getAccountUserView(RedirectAttributes redirectAttributes, Model model, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "page") int page) throws Exception {
        if (Admin.userName.isEmpty()) return "redirect:/admin/login";
        if (Admin.account_manage == 0) {
            redirectAttributes.addFlashAttribute("username", username());
            redirectAttributes.addFlashAttribute("roleName", role());
            redirectAttributes.addFlashAttribute("mess", "Bạn không có quyền sử dụng chức năng này");
            return "redirect:/admin/index";
        }

        List<ResponseAccount> apiResponse = adminService.getAllUser(search, page);

        int pageSize = 2;
        int startIndex = (page - 1) * pageSize;

        Pager pager = new Pager(apiResponse.size(), page, pageSize);

        apiResponse = apiResponse.stream().skip(startIndex).limit(pageSize).collect(Collectors.toList());

        model.addAttribute("search", search);
        model.addAttribute("listUser", apiResponse);
        model.addAttribute("pager", pager);
        model.addAttribute("username", username());
        model.addAttribute("roleName", role());
        return "/admin/accountUser";
    }

    @GetMapping(value = "/admin/profile")
    public String getProfileView(Model model, @RequestParam(value = "search") String username, @RequestParam(value = "action", required = false) int action) throws Exception {
        if (Admin.userName.isEmpty()) return "redirect:/admin/login";

        ApiResponse<ResponseAccount> apiResponse = adminService.getAccountByUserName(username);
        List<ResponseRole> roles = adminService.getAllRole("");
        model.addAttribute("account", apiResponse.getPayload());
        model.addAttribute("roles", roles);
        model.addAttribute("username", username());
        model.addAttribute("roleName", role());
        model.addAttribute("action", action);
        return "/admin/profile";
    }

    @GetMapping(value = "/admin/accountAdmin")
    public String getAccountAdminView(RedirectAttributes redirectAttributes, Model model, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "page") int page) throws Exception {
        if (Admin.userName.isEmpty()) return "redirect:/admin/login";
        if (Admin.admin_manage == 0) {
            redirectAttributes.addFlashAttribute("username", username());
            redirectAttributes.addFlashAttribute("roleName", role());
            redirectAttributes.addFlashAttribute("mess", "Bạn không có quyền sử dụng chức năng này");
            return "redirect:/admin/index";
        }

        List<ResponseAccount> apiResponse = adminService.getAllAdmin(search, page);
        List<ResponseRole> listRole = adminService.getAllRole("");

        int pageSize = 2;
        int startIndex = (page - 1) * pageSize;

        Pager pager = new Pager(apiResponse.size(), page, pageSize);

        apiResponse = apiResponse.stream().skip(startIndex).limit(pageSize).collect(Collectors.toList());

        model.addAttribute("search", search);
        model.addAttribute("listUser", apiResponse);
        model.addAttribute("pager", pager);
        model.addAttribute("username", username());
        model.addAttribute("roleName", role());
        model.addAttribute("listRole", listRole);
        return "/admin/accountAdmin";
    }

    @PostMapping(value = "/admin/account/editProfile")
    public String editProfile(RedirectAttributes redirectAttributes, Model model, @RequestBody ResponseAccount account) throws Exception {

        redirectAttributes.addFlashAttribute("account", account);
        redirectAttributes.addFlashAttribute("username", username());
        redirectAttributes.addFlashAttribute("roleName", role());
        return "redirect:/admin/profile?username=" + account.getUsername();
    }

    @GetMapping(value = "/admin/findUserName")
    @ResponseBody
    public String findUserName(@RequestParam String username) throws Exception {
        return adminService.findByUsername(username);
    }

    @GetMapping(value = "/admin/changeRoleAdmin")
    @ResponseBody
    public String changeRoleAdmin(@RequestParam(value = "user") String user, @RequestParam(value = "role") String role) throws Exception {
        adminService.changeRoleAdmin(user,role);
        return null;
    }

    @PostMapping(value = "/admin/create")
    public ResponseEntity<String> createAdmin(@RequestBody RequestAccountRegister requestAccountRegister) throws Exception {
        adminService.createAdmin(requestAccountRegister);
        return ResponseEntity.ok("success");
    }

    @GetMapping(value = "/admin/account/changeStatus")
    public String changeStatus(RedirectAttributes redirectAttributes, @RequestParam(value = "id") int id) throws Exception {
        String userName = adminService.changeStatus(id);
        redirectAttributes.addFlashAttribute("mess", "success");
        redirectAttributes.addFlashAttribute("username", username());
        redirectAttributes.addFlashAttribute("roleName", role());
        return "redirect:/admin/accountUser?search=" + userName + "&page=1";
    }

    @GetMapping(value = "/admin/account/changeStatusAdmin")
    public String changeStatusAdmin(RedirectAttributes redirectAttributes, @RequestParam(value = "id") int id) throws Exception {
        String userName = adminService.changeStatus(id);
        redirectAttributes.addFlashAttribute("mess", "success");
        redirectAttributes.addFlashAttribute("username", username());
        redirectAttributes.addFlashAttribute("roleName", role());
        return "redirect:/admin/accountAdmin?search=" + userName + "&page=1";
    }

    @GetMapping(value = "/admin/ChangeToRecruiter")
    public String ChangeToRecruiter(Model model, @RequestParam(value = "page") int page) throws Exception {


        model.addAttribute("username", username());
        model.addAttribute("roleName", role());
        return "/admin/changeToRecruiter";
    }

    @GetMapping(value = "/admin/sendOTP")
    @ResponseBody
    public String SendOTP(Model model, @RequestParam(value = "email") String email) throws Exception {
        return adminService.sendOTP(email);
    }

    @PostMapping(value = "/admin/loginUser")
    public String loginUser(RedirectAttributes redirectAttributes,@ModelAttribute("requestAccountLogin") RequestAccountLogin requestAccountLogin) throws Exception {
        requestAccountLogin.setIsAdmin(0);
        ResponseAccount account = adminService.loginAccount(requestAccountLogin);
        if (account == null) {
            redirectAttributes.addFlashAttribute("mess", "login fail");
            return "redirect:/login";
        }else if (account.getStatus() == 1) {
            redirectAttributes.addFlashAttribute("mess", "blocked");
            return "redirect:/login";
        }

        User.name = account.getUsername();
        User.role = account.getRole();
        User.id = account.getId();

        if(User.role.equals("Admin")){
            Admin.userName = account.getUsername();
            Admin.nameRole = account.getRole_id().getName();
            Admin.account_manage = account.getRole_id().getAccount_manage();
            Admin.admin_manage = account.getRole_id().getAdmin_manage();
            Admin.role_manage = account.getRole_id().getRole_manage();
            Admin.id = account.getId();
        }
        redirectAttributes.addFlashAttribute("username", account.getUsername());
        redirectAttributes.addFlashAttribute("role", User.role);
        return "redirect:/";
    }

    @GetMapping(value = "/sendOTPToGetPassword")
    @ResponseBody
    public String sendOTPToGetPassword(@RequestParam(value = "email") String email) throws Exception {
        return adminService.sendOTPToGetPassword(email);
    }

    @GetMapping(value = "/changePassword")
    @ResponseBody
    public String changePassword(@RequestParam(value = "email") String email,@RequestParam(value = "password") String password) throws Exception {
        return adminService.changePassword(email,password);
    }


    @GetMapping(value = "/admin/logout")
    public String logout() throws Exception {
        Admin.id = -1;
        Admin.userName = "";
        Admin.nameRole = "";
        Admin.account_manage = 0;
        Admin.admin_manage = 0;
        Admin.role_manage = 0;
        User.id = -1;
        User.role = "";
        User.name = null;
        return "redirect:/admin/login";
    }

    public String username() {
        return Admin.userName;
    }

    public String role() {
        return Admin.nameRole;
    }
}
