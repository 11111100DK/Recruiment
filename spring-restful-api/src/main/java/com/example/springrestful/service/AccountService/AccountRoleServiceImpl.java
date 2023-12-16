package com.example.springrestful.service.AccountService;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/15/2023
 * Time: 3:04 PM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springrestful.model.entity.Account.AccountRole;
import com.example.springrestful.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRoleServiceImpl implements AccountRoleService{
    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Override
    public List<AccountRole> findAll() throws Exception {
        //return accountRoleRepository.findAll();
        return null;
    }

}
