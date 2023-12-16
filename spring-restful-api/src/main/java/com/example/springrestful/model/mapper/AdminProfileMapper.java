package com.example.springrestful.model.mapper;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/1/2023
 * Time: 10:28 AM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springrestful.model.entity.Admin.AdminProfile;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdminProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminProfileMapper {
    @Mapping(source = "adminProfile.id", target = "id")
    @Mapping(source = "adminProfile.title", target = "title")
    @Mapping(source = "adminProfile.description", target = "description")
    @Mapping(source = "adminProfile.dateCreated", target = "dateCreated")
    @Mapping(source = "adminProfile.dateUpdated", target = "dateUpdated")
    @Mapping(source = "adminProfile.link", target = "link")
    ResponseAdminProfile toResponseAdminProfile(AdminProfile adminProfile);

    List<ResponseAdminProfile> toResponseAdminProfileList(List<AdminProfile> adminProfileList);
}
