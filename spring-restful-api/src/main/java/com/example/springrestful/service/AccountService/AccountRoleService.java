package com.example.springrestful.service.AccountService;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/15/2023
 * Time: 3:03 PM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springrestful.model.entity.Account.AccountRole;

import java.util.List;

public interface AccountRoleService {
    List<AccountRole> findAll()throws Exception;
}
