package com.example.springrestful.model.mapper;

import com.example.springrestful.model.entity.Account.Account;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "account.id", target = "id")
    @Mapping(source = "account.username", target = "username")
    @Mapping(source = "account.role", target = "role")

    @Mapping(source = "account.status", target = "status")
    @Mapping(source = "account.email", target = "email")
    @Mapping(source = "account.phoneNumber", target = "phoneNumber")
    @Mapping(source = "account.fullName", target = "fullName")
    @Mapping(source = "account.address", target = "address")
    @Mapping(source = "account.education", target = "education")
    @Mapping(source = "account.description", target = "description")
    @Mapping(source = "account.avatarImg", target = "avatarImg")
    @Mapping(source = "account.identifyCardNumber", target = "identifyCardNumber")
    @Mapping(source = "account.identifyCardName", target = "identifyCardName")
    @Mapping(source = "account.birthday", target = "birthday", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "account.followers", target = "followers")
    @Mapping(source = "account.following", target = "following")
    @Mapping(source = "account.role_id", target = "role_id")
    ResponseAccount toResponseAccount(Account account);

    List<ResponseAccount> toResponseAccountList(List<Account> account);
}
