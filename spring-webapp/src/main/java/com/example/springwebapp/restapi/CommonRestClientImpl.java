package com.example.springwebapp.restapi;

import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ApiResponse.StatusEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonRestClientImpl implements CommonRestClient {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> T get(String url, Class<T> responseType, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        ResponseEntity<T> responseEntity = restTemplate.getForEntity(builder.toUriString(), responseType, params);
        return responseEntity.getBody();
    }

    @Override
    public <T> List<T> get(String url, ParameterizedTypeReference<List<T>> responseType, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                responseType
        );

        List<T> list = responseEntity.getBody();
        return list;
    }

    @Override
    public <T, R> R post(String url, Class<R> responseType, T requestBody) {
        // Định nghĩa headers (nếu cần)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Thiết lập Content-Type

        // Tạo đối tượng HttpEntity với đối tượng làm phần body
        HttpEntity httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<R> responseEntity = restTemplate.postForEntity(
                url,
                httpEntity,
                responseType
        );

        R r = responseEntity.getBody();
        return r;
    }
    @Override
    public <T, R> R put(String url, Class<R> responseType, T requestBody) {
        // Định nghĩa headers (nếu cần)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Thiết lập Content-Type

        // Tạo đối tượng HttpEntity với đối tượng làm phần body
        HttpEntity httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<R> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                httpEntity,
                responseType
        );

        R r = responseEntity.getBody();
        return r;
    }
    @Override
    public <T> List<T> getAll(String url) {
        try {
            Map<String, String> params = new HashMap<>();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }

            ResponseEntity<ApiResponse<List<T>>> responseEntity = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            ApiResponse<List<T>> apiResponse = responseEntity.getBody();
            if (apiResponse != null && apiResponse.getStatus().equals(StatusEnum.SUCCESS)) {
                return apiResponse.getPayload();
            }

            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public <T, R> List<T> getByCondition(String url, R condition) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("condition", String.valueOf(condition));

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/" + condition);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }

            ResponseEntity<ApiResponse<List<T>>> responseEntity = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            ApiResponse<List<T>> apiResponse = responseEntity.getBody();
            if (apiResponse != null && apiResponse.getStatus().equals(StatusEnum.SUCCESS)) {
                return apiResponse.getPayload();
            }

            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public <T, R> ApiResponse<T> getByConditionUnique(String url, R condition) {
        try {
            ResponseEntity<ApiResponse<T>> responseEntity = restTemplate.exchange(
                    url + "/" + condition,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<T>>() {
                    }
            );

            ApiResponse<T> apiResponse = responseEntity.getBody();
            if (apiResponse != null && apiResponse.getStatus().equals(StatusEnum.SUCCESS)) {
                return apiResponse;
            }

            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public <T, R> ApiResponse<R> post(String url, T requestBody) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity httpEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<ApiResponse<R>> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<>() {
                    }
            );

            ApiResponse response = responseEntity.getBody();
            return response;
        } catch (HttpClientErrorException ex) {
            try {
                if (ex.getStatusCode() != HttpStatus.OK) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ApiResponse apiResponse = objectMapper.readValue(ex.getResponseBodyAsString(), ApiResponse.class);
                    return apiResponse;
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public <T, R, U> ApiResponse<R> put(String url, T requestBody, U condition) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity httpEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<ApiResponse<R>> responseEntity = restTemplate.exchange(
                    url + "/" + condition,
                    HttpMethod.PUT,
                    httpEntity,
                    new ParameterizedTypeReference<>() {
                    }
            );

            ApiResponse response = responseEntity.getBody();
            return response;
        } catch (HttpClientErrorException ex) {
            try {
                if (ex.getStatusCode() != HttpStatus.OK) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ApiResponse apiResponse = objectMapper.readValue(ex.getResponseBodyAsString(), ApiResponse.class);
                    return apiResponse;
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public <T, R> ApiResponse<T> deleteByCondition(String url, R condition) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity httpEntity = new HttpEntity<>(condition, headers);

            ResponseEntity<ApiResponse<T>> responseEntity = restTemplate.exchange(
                    url + "/" + condition,
                    HttpMethod.DELETE,
                    httpEntity,
                    new ParameterizedTypeReference<>() {
                    }
            );

            ApiResponse response = responseEntity.getBody();
            return response;
        } catch (HttpClientErrorException ex) {
            try {
                if (ex.getStatusCode() != HttpStatus.OK) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ApiResponse apiResponse = objectMapper.readValue(ex.getResponseBodyAsString(), ApiResponse.class);
                    return apiResponse;
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public <T, R> ApiResponse<R> postCondition(String url, T condition) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            ResponseEntity<ApiResponse<R>> responseEntity = restTemplate.exchange(
                    url + "/" + condition,
                    HttpMethod.POST,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            ApiResponse response = responseEntity.getBody();
            return response;
        } catch (HttpClientErrorException ex) {
            try {
                if (ex.getStatusCode() != HttpStatus.OK) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ApiResponse apiResponse = objectMapper.readValue(ex.getResponseBodyAsString(), ApiResponse.class);
                    return apiResponse;
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public <T, R> ApiResponse<List<R>> postPagination(String url, T requestBody) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity httpEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<ApiResponse<R>> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<>() {
                    }
            );

            ApiResponse response = responseEntity.getBody();
            return response;
        } catch (HttpClientErrorException ex) {
            try {
                if (ex.getStatusCode() != HttpStatus.OK) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ApiResponse apiResponse = objectMapper.readValue(ex.getResponseBodyAsString(), ApiResponse.class);
                    return apiResponse;
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public <T, R, U> ApiResponse<List<R>> postPaginationAndCondition(String url, T requestBody, U condition) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity httpEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<ApiResponse<R>> responseEntity = restTemplate.exchange(
                    url + "/" + condition,
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<>() {
                    }
            );

            ApiResponse<List<R>> response = (ApiResponse<List<R>>) responseEntity.getBody();
            return response;
        } catch (HttpClientErrorException ex) {
            try {
                if (ex.getStatusCode() != HttpStatus.OK) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ApiResponse apiResponse = objectMapper.readValue(ex.getResponseBodyAsString(), ApiResponse.class);
                    return apiResponse;
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }
}