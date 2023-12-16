package com.example.springrestful.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
    private Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super("Validation failed");
        this.errors = errors;
    }
}
