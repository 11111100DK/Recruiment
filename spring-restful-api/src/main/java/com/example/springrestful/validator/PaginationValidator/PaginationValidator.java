package com.example.springrestful.validator.PaginationValidator;

import com.example.springrestful.model.request.RequestNews.RequestPaginationNews;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PaginationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            RequestPaginationNews requestPaginationNews = (RequestPaginationNews) target;
            if (requestPaginationNews.getLimit() < 0) {
                errors.rejectValue("limit", "error.limit", "limit cannot be negative!");
            }

            if (requestPaginationNews.getOffset() < 0) {
                errors.rejectValue("offset", "error.offset", "offset cannot be negative!");
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
