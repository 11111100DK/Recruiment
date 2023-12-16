package com.example.springwebapp.exception;

public class ExistEmailException extends RuntimeException{
    public ExistEmailException(String message) {
        super(message);
    }
}
