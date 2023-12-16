package com.example.springrestful.controller;

import com.example.springrestful.exception.ApplicationException;
import com.example.springrestful.model.entity.Account.Account;
import com.example.springrestful.model.entity.Account.AccountRole;
import com.example.springrestful.model.mapper.AccountMapper;
import com.example.springrestful.model.mapper.AccountRoleMapper;
import com.example.springrestful.model.request.RequestAccount.RequestAccountChangePass;
import com.example.springrestful.model.request.RequestAccount.RequestAccountEdit;
import com.example.springrestful.model.request.RequestAccount.RequestAccountLogin;
import com.example.springrestful.model.request.RequestAccount.RequestAccountRegister;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import com.example.springrestful.model.response.ApiResponse.StatusEnum;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccountRole;
import com.example.springrestful.service.AccountService.AccountRoleService;
import com.example.springrestful.service.AccountService.AccountService;
import com.example.springrestful.validator.AccountValidator.AccountEditValidator;
import com.example.springrestful.validator.AccountValidator.AccountRegisterValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    @Autowired
    AccountRegisterValidator accountRegisterValidator;
    @Autowired
    AccountEditValidator accountEditValidator;
    @Autowired
    AccountService accountService;

    @Autowired
    AccountRoleService accountRoleService;
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    AccountRoleMapper accountRoleMapper;

    @PostMapping("/login")
    public ResponseEntity<ResponseAccount> loginAccount(@RequestBody RequestAccountLogin requestAccountLogin) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();

            ResponseAccount account = accountService.loginAccount(requestAccountLogin.getUsername(),requestAccountLogin.getPassword(),requestAccountLogin.getIsAdmin());
            apiResponse.ok(account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseAccount>> getAllAccount(@RequestParam(value = "role", required = false) String role) throws Exception {
//        List<Account> accountList = null;
//        if (role == null) {
//            accountList = accountService.findAll();
//        } else {
//            accountList = accountService.findByRole(Integer.parseInt(role));
//        }
        return null;
    }
    @GetMapping("/role/list")
    public ResponseEntity<List<ResponseAccountRole>> getAllRole() throws Exception {
        return new ResponseEntity<>(accountRoleMapper.toResponseAccountRoleList(accountRoleService.findAll()), HttpStatus.OK);

    }
//    @GetMapping("form/{id}")
//    public ResponseEntity<ResponseAccount> returnAccountById(@PathVariable int id) throws Exception {
//        return new ResponseEntity<>(accountMapper.toResponseAccount(accountService.findById(id)), HttpStatus.OK);
//    }
    @GetMapping("form")
    public ResponseEntity<ResponseAccount> returnAccountByIdByRequestParam(@RequestParam int id) throws Exception {
        return new ResponseEntity<>(accountMapper.toResponseAccount(accountService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/form")
    public ResponseEntity<ApiResponse<ResponseAccount>> addAccount(@Valid @RequestBody RequestAccountRegister requestAccountRegister, BindingResult bindingResult) throws Exception {
        accountRegisterValidator.validate(requestAccountRegister, bindingResult);
        ApiResponse apiResponse = new ApiResponse();
        AccountRole role = new AccountRole();
        String requestRole = "ADMIN";
        if (requestRole == "ADMIN") {
            role.setId(1);
        } else if (requestRole == "CANDIDATE") {
            role.setId(2);
        } else {
            role.setId(3);
        }


        if (bindingResult.hasErrors()) {
            apiResponse.ok(new ResponseAccount());
            apiResponse.setMessage(bindingResult.getFieldError());
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (requestAccountRegister != null) {
            ResponseAccount responseAccount = accountService.saveAccount(requestAccountRegister);
            apiResponse.ok(responseAccount);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        apiResponse.ok(new ResponseAccount());
        apiResponse.setMessage("Cannot save because data is null");
        apiResponse.setStatus(StatusEnum.ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("form")
    public ResponseEntity<ApiResponse<ResponseAccount>> editAccount(
            @PathVariable int id, @Valid @RequestBody RequestAccountEdit requestAccountEdit,
            BindingResult bindingResult) throws Exception {
        requestAccountEdit.setId(id);
        accountEditValidator.validate(requestAccountEdit, bindingResult);
        ApiResponse apiResponse = new ApiResponse();
        AccountRole role = new AccountRole();
        String requestRole = "ADMIN";
        if (requestRole == "ADMIN") {
            role.setId(1);
        } else if (requestRole == "CANDIDATE") {
            role.setId(2);
        } else {
            role.setId(3);
        }
        if (bindingResult.hasErrors()) {
            apiResponse.ok(new ResponseAccount());
            apiResponse.setMessage(bindingResult.getFieldError());
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (requestAccountEdit != null) {
            ResponseAccount responseAccount = accountService.saveAccount(requestAccountEdit);
            apiResponse.ok(responseAccount);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

        apiResponse.ok(new ResponseAccount());
        apiResponse.setMessage("Cannot save because data is null");
        apiResponse.setStatus(StatusEnum.ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("delete")
    public ResponseEntity<ApiResponse<ResponseAccount>> deleteAccount(
            @PathVariable int id, @Valid @RequestBody RequestAccountEdit requestAccountEdit,
            BindingResult bindingResult) throws Exception {
        requestAccountEdit.setId(id);
        accountEditValidator.validate(requestAccountEdit, bindingResult);
        ApiResponse apiResponse = new ApiResponse();
        AccountRole role = new AccountRole();
        String requestRole = "ADMIN";
        if (requestRole == "ADMIN") {
            role.setId(1);
        } else if (requestRole == "CANDIDATE") {
            role.setId(2);
        } else {
            role.setId(3);
        }
        if (bindingResult.hasErrors()) {
            apiResponse.ok(new ResponseAccount());
            apiResponse.setMessage(bindingResult.getFieldError());
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (requestAccountEdit != null) {
            ResponseAccount responseAccount = accountService.saveAccount(requestAccountEdit);
            apiResponse.ok(responseAccount);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

        apiResponse.ok(new ResponseAccount());
        apiResponse.setMessage("Cannot save because data is null");
        apiResponse.setStatus(StatusEnum.ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAccount(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        AccountRole role=new AccountRole();
        String requestRole="ADMIN";
        if (requestRole=="ADMIN"){
            role.setId(1);
        } else if (requestRole=="CANDIDATE") {
            role.setId(2);
        }else {
            role.setId(3);
        }
        ResponseAccount responseAccount = accountService.findAccountById(id);
        if (responseAccount != null) {
//            accountService.removeAccountById(id);
            responseAccount.setStatus(0);
            RequestAccountEdit requestAccountEdit = new RequestAccountEdit();
            requestAccountEdit.setUsername(responseAccount.getUsername());
            requestAccountEdit.setRole(responseAccount.getRole());
            requestAccountEdit.setStatus(responseAccount.getStatus());
            requestAccountEdit.setEmail(responseAccount.getEmail());
            requestAccountEdit.setPhoneNumber(responseAccount.getPhoneNumber());
            requestAccountEdit.setFullName(responseAccount.getFullName());
            requestAccountEdit.setAddress(responseAccount.getAddress());
            requestAccountEdit.setEducation(responseAccount.getEducation());
            requestAccountEdit.setDescription(responseAccount.getDescription());
            requestAccountEdit.setAvatarImg(responseAccount.getAvatarImg());
            requestAccountEdit.setIdentifyCardNumber(responseAccount.getIdentifyCardNumber());
            requestAccountEdit.setIdentifyCardName(responseAccount.getIdentifyCardName());
            requestAccountEdit.setBirthday(responseAccount.getBirthday());
            accountService.saveAccount(requestAccountEdit);

            apiResponse.ok(requestAccountEdit);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        apiResponse.setStatus(StatusEnum.ERROR);
        apiResponse.setMessage("Account not found to delete");
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody RequestAccountChangePass requestAccountChangePass) throws Exception {
        boolean rs = accountService.changePassword(requestAccountChangePass);
        if(rs) return new ResponseEntity<>("Đổi mật khẩu thành công", HttpStatus.OK);
        return new ResponseEntity<>("Mật khẩu cũ không khớp", HttpStatus.OK);
    }
}
