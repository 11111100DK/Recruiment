package com.example.springwebapp.Service.AccountService;

import com.example.springwebapp.model.request.RequestAccount.RequestAccountEdit;
import com.example.springwebapp.model.request.RequestAccount.RequestAccountRegister;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccountRole;
import com.example.springwebapp.restapi.CommonRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private CommonRestClient commonRestClient;

    private String apiUrl = "http://localhost:8080/api/account";
    @Override
    public List<ResponseAccount> findAllAccounts () {
        try {
            Map<String, String> params = new HashMap<>();

            List<ResponseAccount> response = commonRestClient.get(apiUrl+"/list", new ParameterizedTypeReference<>() {}, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public List<ResponseAccount> findByRole(int role) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("role", String.valueOf(role));

            List<ResponseAccount> response = commonRestClient.get(apiUrl+"/list", new ParameterizedTypeReference<>() {}, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public List<ResponseAccountRole> findAllRole  () {
        try {
            Map<String, String> params = new HashMap<>();

            List<ResponseAccountRole> response = commonRestClient.get(apiUrl+"/role/list", new ParameterizedTypeReference<>() {}, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public ResponseAccount findById(int id) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(id));

            ResponseAccount response = commonRestClient.get(apiUrl+"/form", ResponseAccount.class, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public ApiResponse<ResponseAccount> save(RequestAccountRegister account) {
        try {
            ApiResponse response = commonRestClient.post(apiUrl+"/form", ApiResponse.class, account);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @Override
    public ApiResponse<ResponseAccount> save(RequestAccountEdit account) {
        try {
            ApiResponse response = commonRestClient.post(apiUrl+"/form", ApiResponse.class, account);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ApiResponse<ResponseAccount> save(ResponseAccount account) {
        try {
            ApiResponse response = commonRestClient.post(apiUrl+"/delete", ApiResponse.class, account);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @Override
    public ApiResponse<ResponseAccount> update(ResponseAccount account) {
        try {
            ApiResponse response = commonRestClient.put(apiUrl+"/form", ApiResponse.class, account);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @Override
    public ApiResponse<ResponseAccount> update(RequestAccountEdit account) {
        try {
            ApiResponse response = commonRestClient.post(apiUrl+"/form", ApiResponse.class, account);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
