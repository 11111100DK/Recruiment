package com.example.springwebapp.model.Mapper;

import com.example.springwebapp.model.model.Role;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseJob.ResponseJob;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class AccountMapper {
    public static ResponseAccount toResponseAccount (Object object) {
        try {
            LinkedHashMap<String, Object> account = (LinkedHashMap<String, Object>) object;
            System.out.println(account);
            ResponseAccount responseAccount = new ResponseAccount();
            responseAccount.setId((Integer) account.get("id"));
            responseAccount.setUsername((String) account.get("username"));
            responseAccount.setRole((String) account.get("role"));
            responseAccount.setStatus((Integer) account.get("status"));
            responseAccount.setEmail((String) account.get("email")); // Thiết lập giá trị từ dữ liệu account
            responseAccount.setPhoneNumber((String) account.get("phoneNumber")); // Thiết lập giá trị từ dữ liệu account
            responseAccount.setFullName((String) account.get("fullName")); // Thiết lập giá trị từ dữ liệu account
            responseAccount.setAddress((String) account.get("address")); // Thiết lập giá trị từ dữ liệu account
            responseAccount.setEducation((String) account.get("education")); // Thiết lập giá trị từ dữ liệu account
            responseAccount.setDescription((String) account.get("description")); // Chuyển đổi giá trị ngày từ dữ liệu account
            responseAccount.setAvatarImg((String) account.get("avatarImg")); // Chuyển đổi giá trị ngày từ dữ liệu account
            responseAccount.setIdentifyCardNumber((String) account.get("identifyCardNumber")); // Chuyển đổi giá trị ngày từ dữ liệu account
            responseAccount.setIdentifyCardName((String) account.get("identifyCardName")); // Thiết lập giá trị từ dữ liệu account
            responseAccount.setBirthday((String) account.get("birthday")); // Thiết lập giá trị từ dữ liệu account
            responseAccount.setFollowers((Integer) account.get("followers")); // Thiết lập giá trị từ dữ liệu account
            responseAccount.setFollowing((Integer) account.get("following")); // Thiết lập giá trị từ dữ liệu account
            responseAccount.setRole_id((Role) account.get("role_id")); // Thiết lập giá trị từ dữ liệu account
            System.out.println("responseAccount "+responseAccount.getId());
            return responseAccount;
        } catch (Exception e) {
            return null;
        }
    }
}
