package com.example.springwebapp.restapi;

import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
import java.util.Map;

public interface CommonRestClient {
    <T> T get(String url, Class<T> responseType, Map<String, String> params);

    <T> List<T> get(String url, ParameterizedTypeReference<List<T>> responseType, Map<String, String> params);

    <T, R> R post(String url, Class<R> responseType, T requestBody);
    <T, R> R put(String url, Class<R> responseType, T requestBody);
    <T> List<T> getAll(String url);
    <T, R> List<T> getByCondition (String url, R condition);
    <T, R> ApiResponse<T> getByConditionUnique (String url, R condition);
    <T, R> ApiResponse<R> post(String url, T requestBody);
    <T, R, U> ApiResponse<R> put(String url, T requestBody, U condition);
    <T, R> ApiResponse<T> deleteByCondition(String url, R condition);


    <T, R> ApiResponse<R> postCondition(String url, T condition);
    <T, R> ApiResponse<List<R>> postPagination(String url, T requestBody);
    <T, R, U> ApiResponse<List<R>> postPaginationAndCondition(String url, T requestBody, U condition);

}
