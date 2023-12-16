package com.example.springwebapp.Service.AdminService;

import com.example.springwebapp.model.request.RequestAccount.RequestAccountLogin;
import com.example.springwebapp.model.request.RequestAccount.RequestAccountRegister;
import com.example.springwebapp.model.request.RequestAdmin.RequestAdmin;
import com.example.springwebapp.model.request.RequestRole.RequestRole;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springwebapp.model.response.ResponseDashboard.ResponseDashboard;
import com.example.springwebapp.model.response.ResponseRole.ResponseRole;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
//    int countNumberAccessWebsite () throws Exception;
//    int countNumberUserRegister () throws Exception;
//    int countNumberUserLogin () throws Exception;
//    int countNumberTransactionsSuccessful () throws Exception;
//    int countMostPopularJob () throws Exception;
//    int countMostPopularRecruitment () throws Exception;
//    int countMostPopularSkill () throws Exception;
//    int countMostPopularCategory () throws Exception;

    //===========================Admin=============================
    ResponseAccount loginAccount(RequestAccountLogin requestAccountLogin) throws Exception;
    ResponseAdmin findById(int id);
    List<ResponseAdmin> findAll();
    List<ResponseAdmin> findByRole(String role);
    List<ResponseAdmin> findByStatus(int status);

    ApiResponse<ResponseAdmin> update(RequestAdmin admin);

    //    List<ResponseAdmin> findByManagedBy(Integer managedBy)throws Exception;
//    List<ResponseAdmin> findByAccountStatus(int accountStatus);
    List<ResponseAdmin> findByAccount(int accountId);
    ApiResponse<ResponseAdmin> save(RequestAdmin admin);
//    void removeById (int id) throws Exception;
    //===========================AdminProfile=============================
//    ResponseAdminProfile findProfileById(int id)throws Exception;
//    List<ResponseAdminProfile> findAllProfile()throws Exception;
//
//    ResponseAdminProfile saveProfile(RequestAdminProfile admin)throws Exception;
//    void removeProfileById(int id) throws Exception;

    //====================Role====================
    List<ResponseRole> getAllRole(String name) throws Exception;
    ApiResponse<ResponseRole> getRoleById(int id) throws Exception;
    ApiResponse<ResponseRole> deleteRoleById(int id) throws Exception;
    ApiResponse<ResponseRole> editRole(RequestRole requestRole) throws Exception;
    ApiResponse<ResponseRole> addRole(RequestRole requestRole) throws Exception;

    //====================User====================
    List<ResponseAccount> getAllUser(String userName, int page) throws Exception;
    String changeStatus(int id) throws Exception;
    ApiResponse<ResponseAccount> getAccountByUserName(String username)throws Exception;
    List<ResponseAccount> getAllAdmin(String userName, int page) throws Exception;
    String sendOTP(String mail);
    String findByUsername(String username);
    void createAdmin(RequestAccountRegister requestAccountRegister);
    String changeRoleAdmin(String user, String role);
    String changePassword(String username, String password);
    String sendOTPToGetPassword(String email);

    //====================Statitics====================
    void increaseAccessWebsite();
    ResponseDashboard countStatistics();
}
