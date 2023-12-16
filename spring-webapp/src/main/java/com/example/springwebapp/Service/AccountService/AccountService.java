package com.example.springwebapp.Service.AccountService;

import com.example.springwebapp.model.request.RequestAccount.RequestAccountEdit;
import com.example.springwebapp.model.request.RequestAccount.RequestAccountRegister;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccountRole;

import java.util.List;

public interface AccountService {
//    ResponseAccount saveAccount (RequestAccountRegister account) throws Exception;
//    ResponseAccount saveAccount (RequestAccountEdit account) throws Exception;
//    void removeAccountById (int id) throws Exception;
    List<ResponseAccount> findAllAccounts ();
//    ResponseAccount findAccountById (int id) throws Exception;
//    ResponseAccount findByUsername (String username) throws Exception;
    List<ResponseAccount> findByRole(int role);
    List<ResponseAccountRole> findAllRole ();
    ResponseAccount findById(int id);
    ApiResponse<ResponseAccount> save(RequestAccountRegister account);
    ApiResponse<ResponseAccount> save(RequestAccountEdit account);
    ApiResponse<ResponseAccount> save(ResponseAccount account);
    ApiResponse<ResponseAccount> update(ResponseAccount account);
//    ApiResponse<ResponseAccount> update(ResponseAccount account);
    ApiResponse<ResponseAccount> update(RequestAccountEdit account);
}
