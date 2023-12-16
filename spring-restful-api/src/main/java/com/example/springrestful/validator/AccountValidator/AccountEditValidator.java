package com.example.springrestful.validator.AccountValidator;

import com.example.springrestful.model.request.RequestAccount.RequestAccountEdit;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import com.example.springrestful.service.AccountService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountEditValidator implements Validator {
    @Autowired
    AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            RequestAccountEdit requestAccountEdit = (RequestAccountEdit) target;
            ResponseAccount account = accountService.findByUsername(requestAccountEdit.getUsername());
            if (account != null && (!requestAccountEdit.getUsername().equals(account.getUsername()))) {
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
