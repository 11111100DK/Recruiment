package com.example.springwebapp.Service.AdminService;



import com.example.springwebapp.model.request.RequestAccount.RequestAccountLogin;
import com.example.springwebapp.model.request.RequestAccount.RequestAccountRegister;
import com.example.springwebapp.model.request.RequestAdmin.RequestAdmin;
import com.example.springwebapp.model.request.RequestChangeStatus.ResquestChangeStatus;
import com.example.springwebapp.model.request.RequestRole.RequestRole;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springwebapp.model.response.ResponseDashboard.ResponseDashboard;
import com.example.springwebapp.model.response.ResponseRole.ResponseRole;
import com.example.springwebapp.restapi.CommonRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private CommonRestClient commonRestClient;
    private String apiUrl= "http://localhost:8080/api/admin";
    private String apiUrlLogin= "http://localhost:8080/api";


    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public List<ResponseAdmin> findAll() {
        try {
            Map<String, String> params = new HashMap<>();

            List<ResponseAdmin> response = commonRestClient.get(apiUrl+"/list", new ParameterizedTypeReference<>() {}, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public List<ResponseAdmin> findByRole(String role) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("role", role);

            List<ResponseAdmin> response = commonRestClient.get(apiUrl+"/list", new ParameterizedTypeReference<>() {}, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<ResponseAdmin> findByStatus(int status) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("status", String.valueOf(status));

            List<ResponseAdmin> response = commonRestClient.get(apiUrl+"/list", new ParameterizedTypeReference<>() {}, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ResponseAccount loginAccount(RequestAccountLogin requestAccountLogin) throws Exception {
        try {
            //ApiResponse response = restTemplate.getForObject(apiUrlLogin+"/account/login", ApiResponse.class);
            return commonRestClient.post(apiUrlLogin+"/account/login",ResponseAccount.class, requestAccountLogin);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseAdmin findById(int id) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(id));

            ResponseAdmin response = commonRestClient.get(apiUrl+"/form/id", ResponseAdmin.class, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ApiResponse<ResponseAdmin> save(RequestAdmin admin) {
        try {
            ApiResponse response = commonRestClient.post(apiUrl+"/form", ApiResponse.class, admin);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ResponseRole> getAllRole(String name) throws Exception {
        return commonRestClient.getAll(apiUrl+"/role?name="+name);
    }
    @Override
    public ApiResponse<ResponseRole> getRoleById(int id) throws Exception{
        return commonRestClient.getByConditionUnique(apiUrl+"/role",id);
    }
    @Override
    public ApiResponse<ResponseRole> deleteRoleById(int id) throws Exception{
        return commonRestClient.deleteByCondition(apiUrl+"/role",id);
    }

    @Override
    public ApiResponse<ResponseRole> editRole(RequestRole requestRole) throws Exception {
        return commonRestClient.post(apiUrl + "/role/editRole", requestRole);
    }

    @Override
    public ApiResponse<ResponseRole> addRole(RequestRole requestRole) throws Exception {
        return commonRestClient.post(apiUrl + "/role",requestRole);
    }

    @Override
    public List<ResponseAccount> getAllUser(String userName, int page) throws Exception {
        return commonRestClient.getAll(apiUrl+"/accountUser?userName="+userName+"&page="+page);
    }

    @Override
    public String changeStatus(int id) throws Exception {
        ResquestChangeStatus rq = new ResquestChangeStatus();
        rq.setId(id);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<ResquestChangeStatus> requestEntity = new HttpEntity<>(rq, headers);

        ResponseEntity<ApiResponse> responseEntity = restTemplate.exchange(
                apiUrl + "/accountUser/changeStatus",
                HttpMethod.POST,
                requestEntity,
                ApiResponse.class);
        System.out.println(Objects.requireNonNull(responseEntity.getBody()).getPayload());
        return Objects.requireNonNull(responseEntity.getBody()).getPayload().toString();
    }

    @Override
    public ApiResponse<ResponseAccount> getAccountByUserName(String username) throws Exception {
        return commonRestClient.getByConditionUnique(apiUrl+"/getAccountByUserName",username);
    }

    @Override
    public List<ResponseAccount> getAllAdmin(String userName, int page) throws Exception {
        return commonRestClient.getAll(apiUrl+"/accountAdmin?userName="+userName+"&page="+page);
    }

    @Override
    public ApiResponse<ResponseAdmin> update(RequestAdmin admin) {
        try {
            ApiResponse response = commonRestClient.put(apiUrl+"/form", ApiResponse.class, admin);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @Override
    public List<ResponseAdmin> findByAccount(int accountId){
        try {
            Map<String, String> params = new HashMap<>();
            params.put("account", String.valueOf(accountId));

            List<ResponseAdmin> response = commonRestClient.get(apiUrl+"/list", new ParameterizedTypeReference<>() {}, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String sendOTP(String mail) {
        Map<String, String> params = new HashMap<>();
        params.put("email", mail);
        return commonRestClient.get(apiUrl+"/sendOTP",ApiResponse.class, params).getPayload().toString();
    }

    @Override
    public String findByUsername(String username) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        return commonRestClient.get(apiUrl+"/findUserName",ApiResponse.class, params).getPayload().toString();
    }

    @Override
    public void createAdmin(RequestAccountRegister requestAccountRegister) {
        commonRestClient.post(apiUrl+"/createAdmin",requestAccountRegister);
    }

    @Override
    public String changeRoleAdmin(String user, String role) {
        Map<String, String> params = new HashMap<>();
        params.put("user", user);
        params.put("role", role);
        commonRestClient.get(apiUrl+"/changeRoleAdmin",String.class,params);
        return null;
    }

    @Override
    public String changePassword(String email, String password) {
        RequestAccountLogin rq = new RequestAccountLogin();
        rq.setPassword(password);
        rq.setUsername(email);
        ApiResponse api = commonRestClient.put(apiUrl+"/changePassword",ApiResponse.class,rq);
        return api.getPayload().toString();
    }

    @Override
    public String sendOTPToGetPassword(String email) {
        ApiResponse<String> api = commonRestClient.getByConditionUnique(apiUrl+"/sendOTPToGetPassword",email);
        return api.getPayload();
    }

    @Override
    public void increaseAccessWebsite() {
        Map<String, String> params = new HashMap<>();
        commonRestClient.get(apiUrl+"/accessLog",String.class,params);
    }

    @Override
    public ResponseDashboard countStatistics() {
        Map<String, String> params = new HashMap<>();
        LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>) commonRestClient.get(apiUrl+"/countStatistics",ApiResponse.class,params).getPayload();
        ResponseDashboard response = new ResponseDashboard();
        response.setCountJobs((Integer)result.get("countJobs"));
        response.setCountUser((Integer)result.get("countUser"));
        response.setCountRecruiters((Integer)result.get("countRecruiters"));
        response.setCountUsers((Integer)result.get("countUsers"));
        return response;
    }
}
