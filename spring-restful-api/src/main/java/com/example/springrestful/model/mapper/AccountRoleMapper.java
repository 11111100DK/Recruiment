package com.example.springrestful.model.mapper;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/15/2023
 * Time: 2:38 PM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springrestful.model.entity.Account.AccountRole;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccountRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountRoleMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    ResponseAccountRole toResponseAccountRole(AccountRole role);
    List<ResponseAccountRole> toResponseAccountRoleList(List<AccountRole> roles);
}
