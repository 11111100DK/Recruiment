package com.example.springrestful.service.AdminService;

import com.example.springrestful.model.entity.AccessLog.AccessLog;
import com.example.springrestful.model.entity.Admin.Admin;
import com.example.springrestful.model.entity.Role.Role;
import com.example.springrestful.model.request.RequestAdmin.RequestAdmin;
import com.example.springrestful.model.request.RequestAdmin.RequestAdminProfile;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdminProfile;
import com.example.springrestful.model.response.ResponseAdmin.ResponseCountUserLogin;
import com.example.springwebapp.model.response.ResponseDashboard.ResponseDashboard;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;

public interface AdminService {
    void increaseAccessWebsite() throws Exception;
    List<AccessLog> countNumberAccessWebsite (Date startDate, Date endDate) throws Exception;
    List<Integer> countNumberUserRegister () throws Exception;
    List<ResponseCountUserLogin> countNumberUserLogin (LocalDate startDate, LocalDate endDate) throws Exception;
    int countNumberTransactionsSuccessful () throws Exception;
    int countMostPopularJob () throws Exception;
    int countMostPopularRecruitment () throws Exception;
    int countMostPopularSkill () throws Exception;
    int countMostPopularCategory () throws Exception;

    //===========================Admin=============================
    Admin findById(int id)throws Exception;
    List<Admin> findAll()throws Exception;
    //    List<Admin> findByRole(String role)throws Exception;
//    List<ResponseAdmin> findByStatus(int status)throws Exception;
//    List<ResponseAdmin> findByManagedBy(Integer managedBy)throws Exception;
////    List<ResponseAdmin> findByAccountStatus(int accountStatus);
    List<Admin> findByAccount(int accountId)throws Exception;
    ResponseAdmin save(RequestAdmin admin);
    void removeById (int id) throws Exception;
    //===========================AdminProfile=============================
//    ResponseAdminProfile findProfileById(int id)throws Exception;
//    List<AdminProfile> findAllProfile()throws Exception;

    //    ResponseAdminProfile saveProfile(RequestAdminProfile admin)throws Exception;
//    void removeProfileById(int id) throws Exception;
    //===========================Role=============================
    Role createRole(Role role) throws Exception;
    Role editRole(Role role) throws Exception;
    void removeRole(int id) throws Exception;
    List<Role> findAllRole (String name) throws Exception;
    Role findRoleById (int id) throws Exception;
    Role findByRolename (String name) throws Exception;
    int checkRoleExisted(int id, String name)throws Exception;
    int countAccountOfRole(int id)throws Exception;

    //===========================User=============================
    List<ResponseAccount> findAllUser (String userName, int page) throws Exception;

    void changeStatus(int id) throws Exception;

    ResponseAccount getAccountByUserName(String username) throws Exception;

    //===========================Admin=============================
    List<ResponseAccount> findAllAdmin (String userName, int page) throws Exception;
    String changeRoleAdmin (int user, int role) throws Exception;
    void changePassword(String username, String password);
    String sendOTPToGetPassword(String email);

    //===========================Statitics=============================
    ResponseDashboard countStatistics();
}
