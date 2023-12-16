package com.example.springrestful.repository;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/1/2023
 * Time: 10:31 AM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springrestful.model.entity.Admin.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminProfileResponsitory extends JpaRepository<AdminProfile,Integer> {
    AdminProfile findById (int id);
}
