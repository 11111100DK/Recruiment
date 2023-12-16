package com.example.springrestful.service.AccountService;

import com.example.springrestful.model.entity.Account.Account;
import com.example.springrestful.model.entity.Account.AccountRole;
import com.example.springrestful.model.request.RequestAccount.RequestAccountChangePass;
import com.example.springrestful.model.request.RequestAccount.RequestAccountEdit;
import com.example.springrestful.model.request.RequestAccount.RequestAccountRegister;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;

import java.util.List;

public interface AccountService {
    ResponseAccount saveAccount (RequestAccountRegister account) throws Exception;
    ResponseAccount saveAccount (RequestAccountEdit account) throws Exception;
    void removeAccountById (int id) throws Exception;
    List<ResponseAccount> findAllAccounts () throws Exception;
    ResponseAccount findAccountById (int id) throws Exception;
    ResponseAccount findByUsername (String username) throws Exception;
    List<Account> findAll()throws Exception;
    List<Account> findByRole(int role)throws Exception;
    Account findById(int id)throws Exception;
    ResponseAccount loginAccount (String username, String password, int isAdmin) throws Exception;
    String sendOtp(String email);

    String findUsername (String username) throws Exception;
    boolean changePassword(RequestAccountChangePass requestAccountChangePass);
}
