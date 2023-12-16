package com.example.springrestful.validator.SupportQuestionValidator;
import com.example.springrestful.model.entity.Account.Account;
import com.example.springrestful.model.request.RequestSupportQuestion.RequestSupportQuestion;
import com.example.springrestful.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SupportQuestionValidator implements Validator {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return RequestSupportQuestion.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestSupportQuestion supportQuestion = (RequestSupportQuestion) target;

        Account account = accountRepository.findById(supportQuestion.getAccountId());
        if (account == null) {
            errors.rejectValue("id", "error.id", "Account does not exist!");
        }
    }
}
