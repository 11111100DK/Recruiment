package com.example.springwebapp.exception;

public class ValidatorException extends RuntimeException {
    public ValidatorException(String message) {
        super(message);
    }
}
