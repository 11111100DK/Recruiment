package com.example.springrestful.repository;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/15/2023
 * Time: 2:48 PM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springrestful.model.entity.Account.AccountRole;
import com.example.springrestful.model.entity.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleRepository extends JpaRepository<Role, Integer> {
    AccountRole findById (int id);
    @Query("SELECT r FROM Role r WHERE r.id = :id")
    Role findByRoleId(@Param("id") int id);
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Role findByName(@Param("name") String name);
    @Query("SELECT r FROM Role r WHERE r.name LIKE %:name%")
    List<Role> searchRoleName(@Param("name") String name);
    @Query("SELECT r FROM Role r WHERE r.name = :name AND id <> :id")
    List<Role> checkRoleExisted(@Param("name") String name, @Param("id") int id);
    @Query(value = "SELECT COUNT(a.id) FROM Account a WHERE a.role_id = :id", nativeQuery = true)
    int countAccountOfRole(@Param("id") int id);

    @Query("SELECT COUNT(a) FROM Account a WHERE a.role <> 'Admin'")
    int countNumberUserRegister();
    @Query("SELECT COUNT(a) FROM Account a WHERE a.role = 'Recruiter'")
    int countNumberRecruiterRegister();
    @Query("SELECT COUNT(a) FROM Account a WHERE a.role = 'Candidate'")
    int countNumberCandidateRegister();

}
