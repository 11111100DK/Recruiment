package com.example.springrestful.handler;

import com.example.springrestful.exception.*;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handleNotFoundException(NotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("errorCode", "404");
        error.put("errorMessage", ex.getMessage());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleValidationException(ValidationException ex) {
        Map<String, String> error = new HashMap<>(ex.getErrors());
        error.put("errorCode", "400");

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse handleApplicationException(ApplicationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("errorCode", "500");
        error.put("errorMessage",  ex.getMessage());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ApiResponse handleInvalidTokenException(InvalidTokenException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("errorCode", "403");
        error.put("errorMessage", ex.getMessage());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }
    @ExceptionHandler(ExistEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiResponse handleExistEmailException(ExistEmailException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("errorCode", "409");
        error.put("errorMessage", ex.getMessage());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }
}
