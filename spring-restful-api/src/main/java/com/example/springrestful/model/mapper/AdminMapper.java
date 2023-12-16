package com.example.springrestful.model.mapper;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/1/2023
 * Time: 10:28 AM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springrestful.model.entity.Admin.Admin;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdmin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    @Mapping(source = "admin.id", target = "id")
    @Mapping(source = "admin.description", target = "description")
    @Mapping(source = "admin.experienceYear", target = "experienceYear")
    @Mapping(source = "admin.major", target = "major")
    @Mapping(source = "admin.role", target = "role")
    @Mapping(source = "admin.status", target = "status")
    @Mapping(source = "admin.managedBy", target = "managedBy")

    @Mapping(source = "admin.accountAdmin.fullName", target = "fullName")
    @Mapping(source = "admin.accountAdmin.id", target = "accountId")
    ResponseAdmin toResponseAdmin(Admin admin);

    List<ResponseAdmin> toResponseAdminList(List<Admin> adminList);
}
