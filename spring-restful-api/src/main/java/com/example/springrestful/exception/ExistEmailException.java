package com.example.springrestful.exception;

public class ExistEmailException extends RuntimeException{
    public ExistEmailException(String message) {
        super(message);
    }
}
