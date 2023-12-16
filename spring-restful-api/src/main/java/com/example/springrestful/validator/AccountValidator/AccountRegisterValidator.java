package com.example.springrestful.validator.AccountValidator;

import com.example.springrestful.model.entity.Role.Role;
import com.example.springrestful.model.request.RequestAccount.RequestAccountRegister;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import com.example.springrestful.service.AccountService.AccountService;
import com.example.springrestful.service.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountRegisterValidator implements Validator {
    @Autowired
    AccountService accountService;
    @Autowired
    AdminService adminService;

    public boolean validateDelete(Object target) {
        try {
            Role role = (Role) target;
            int rs = adminService.countAccountOfRole(role.getId());
            if (rs > 0) return false;
            else{
                adminService.removeRole(role.getId());
                return true;
            }
        } catch (Exception e) {
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            RequestAccountRegister requestAccountRegister = (RequestAccountRegister) target;
            ResponseAccount account = accountService.findByUsername(requestAccountRegister.getUsername());
            if (account != null) {
                errors.rejectValue("username", "error.username", "username existed!");
            }
        } catch (Exception e) {
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
