package com.example.springrestful.service.SupportAndContactService;

import com.example.springrestful.exception.ApplicationException;
import com.example.springrestful.exception.NotFoundException;
import com.example.springrestful.exception.ValidationException;
import com.example.springrestful.model.entity.Account.Account;
import com.example.springrestful.model.entity.SupportQuestion.SupportQuestion;
import com.example.springrestful.model.mapper.SupportQuestionMapper;
import com.example.springrestful.model.request.RequestSupportQuestion.RequestSupportQuestion;
import com.example.springrestful.model.response.ResponseSupportQuestion.ResponseSupportQuestion;
import com.example.springrestful.repository.AccountRepository;
import com.example.springrestful.repository.SupportAndContactRepository;
import com.example.springrestful.util.ValidatorUtil;
import com.example.springrestful.validator.SupportQuestionValidator.SupportQuestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

@Service
public class SupportAndContactServiceImpl implements SupportAndContactService {

    @Autowired
    SupportAndContactRepository supportAndContactRepository;

    @Autowired
    SupportQuestionMapper supportQuestionMapper;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SupportQuestionValidator supportQuestionValidator;

    @Autowired
    ValidatorUtil validatorUtil;

    @Override
    public List<SupportQuestion> findAll() {
        try {
            return supportAndContactRepository.findAll();
        } catch (ApplicationException ex) {
            throw ex;
        }
    }

    @Override
    public List<SupportQuestion> findByAccount(int accountId) {
        try {
            Account account = new Account();
            account.setId(accountId);

            return supportAndContactRepository.findByAccount(account);
        } catch (ApplicationException ex) {
            throw ex;
        }
    }

    @Override
    public SupportQuestion findById(int id) {
        try {
            return supportAndContactRepository.findById(id).orElse(null);
        } catch (ApplicationException ex) {
            throw ex;
        }
    }

    @Override
    public ResponseSupportQuestion saveQuestion(RequestSupportQuestion requestSupportQuestion, BindingResult bindingResult) {
        try {
            // Validator
            supportQuestionValidator.validate(requestSupportQuestion, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            // Map to entity
            SupportQuestion supportQuestion = supportQuestionMapper.toEntity(requestSupportQuestion);
            Account account = accountRepository.findById(requestSupportQuestion.getAccountId());
            supportQuestion.setAccount(account);

            // Save
            supportAndContactRepository.saveAndFlush(supportQuestion);

            // Map to response
            return supportQuestionMapper.toResponseSupportQuestion(supportQuestion);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(); // Handle other exceptions
        }
    }

    @Override
    public ResponseSupportQuestion updateQuestion(RequestSupportQuestion requestSupportQuestion, BindingResult bindingResult) {
        try {
            SupportQuestion existingSupportQuestion = findById(requestSupportQuestion.getId());
            if (existingSupportQuestion == null) {
                throw new NotFoundException("Support Question Not Found");
            }

            // Validator
            supportQuestionValidator.validate(requestSupportQuestion, bindingResult);
            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            // Map to entity
            SupportQuestion supportQuestion = supportQuestionMapper.toEntity(requestSupportQuestion);
            Account account = accountRepository.findById(requestSupportQuestion.getAccountId());
            supportQuestion.setAccount(account);

            // Update
            supportAndContactRepository.saveAndFlush(supportQuestion);

            // Map to response
            return supportQuestionMapper.toResponseSupportQuestion(supportQuestion);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(); // Handle other exceptions
        }
    }

    @Override
    public void deleteQuestionById(int id) {
        try {
            SupportQuestion supportQuestion = findById(id);
            if (supportQuestion == null) {
                throw new NotFoundException("Support Question Not Found");
            }

            // Update
            supportAndContactRepository.delete(supportQuestion);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(); // Handle other exceptions
        }
    }

    @Override
    public List<RequestSupportQuestion> findQuestionByAccountUserName(String username) throws Exception {
        return supportQuestionMapper.toRequestSupportQuestionList(supportAndContactRepository.findByAccountUsername(username));
    }

    @Override
    public List<RequestSupportQuestion> findQuestionByAccountEmail(String email) throws Exception {
        return supportQuestionMapper.toRequestSupportQuestionList(supportAndContactRepository.findByAccountEmail(email));
    }
}
