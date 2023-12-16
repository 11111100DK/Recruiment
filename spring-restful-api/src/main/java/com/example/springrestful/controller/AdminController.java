package com.example.springrestful.controller;

import com.example.springrestful.exception.ApplicationException;
import com.example.springrestful.exception.NotFoundException;
import com.example.springrestful.exception.ValidationException;
import com.example.springrestful.model.entity.Admin.Admin;
import com.example.springrestful.model.entity.Role.Role;
import com.example.springrestful.model.entity.UserLogin.UserLogin;
import com.example.springrestful.model.mapper.AccountMapper;
import com.example.springrestful.model.mapper.AdminMapper;
import com.example.springrestful.model.request.RequestAccount.RequestAccountEdit;
import com.example.springrestful.model.request.RequestAccount.RequestAccountLogin;
import com.example.springrestful.model.request.RequestAccount.RequestAccountRegister;
import com.example.springrestful.model.request.RequestAdmin.RequestAdmin;
import com.example.springrestful.model.request.RequestAdmin.RequestAdminProfile;
import com.example.springrestful.model.request.RequestChangeStatus.RequestChangeStatus;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import com.example.springrestful.model.response.ApiResponse.StatusEnum;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdminProfile;
import com.example.springrestful.service.AccountService.AccountService;
import com.example.springrestful.service.AdminService.AdminService;
import com.example.springrestful.util.ValidatorUtil;
import com.example.springrestful.validator.AccountValidator.AccountEditValidator;
import com.example.springrestful.validator.AccountValidator.AccountRegisterValidator;
import com.example.springwebapp.model.response.ResponseDashboard.ResponseDashboard;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    AccountRegisterValidator accountRegisterValidator;
    @Autowired
    AccountEditValidator accountEditValidator;
    @Autowired
    AccountService accountService;

    @Autowired
    AdminService adminService;
    @Autowired
    ValidatorUtil validatorUtil;
    @Autowired
    AccountMapper accountMapper;

    @GetMapping(value = "/countStatistics")
    public ResponseEntity<ApiResponse<ResponseDashboard>> getDashboard() throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(adminService.countStatistics());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    //++++++++++++++++++++++++++++++User+++++++++++++++++++++++++++++++
    @GetMapping(value = "/accountUser")
    public ResponseEntity<ApiResponse<List<ResponseAccount>>> getAllUser(@RequestParam(value = "userName") String userName, @RequestParam(value = "page") int page) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(adminService.findAllUser(userName,page));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping(value = "/accountUser/changeStatus")
    public ResponseEntity<ApiResponse<String>> changeStatus(@RequestBody RequestChangeStatus requestChangeStatus) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();

            adminService.changeStatus(requestChangeStatus.getId());
            apiResponse.ok(accountMapper.toResponseAccount(accountService.findById(requestChangeStatus.getId())).getUsername());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping(value = "/getAccountByUserName/{username}")
    public ResponseEntity<ApiResponse<ResponseAccount>> getAccountByUserName(@PathVariable String username) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(adminService.getAccountByUserName(username));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping(value = "/sendOTPToGetPassword/{email}")
    public ResponseEntity<ApiResponse<String>> sendOTPToGetPassword(@PathVariable String email) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(adminService.sendOTPToGetPassword(email));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<ApiResponse<String>> changePassword(@RequestBody RequestAccountLogin requestAccountLogin) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            adminService.changePassword(requestAccountLogin.getUsername(),requestAccountLogin.getPassword());
            apiResponse.ok("Change Password is successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    //+++++++++++++++++++++++++++++++++++++++++Admin+++++++++++++++++++++++++++++++++++++++
    @GetMapping(value = "/accountAdmin")
    public ResponseEntity<ApiResponse<List<ResponseAccount>>> getAllAdmin(@RequestParam(value = "userName") String userName, @RequestParam(value = "page") int page) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(adminService.findAllAdmin(userName,page));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping("/form/{id}")
    public ResponseEntity<ResponseAdmin> returnAdminById(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(adminMapper.toResponseAdmin(adminService.findById(id)), HttpStatus.OK);
    }
    @GetMapping(value = "/form/id")
    public ResponseEntity<ResponseAdmin> getByIdByRequestParam(@RequestParam int id) throws Exception {
        return new ResponseEntity<>(adminMapper.toResponseAdmin(adminService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<ApiResponse<String>> addAdmin(@RequestBody RequestAccountRegister requestAccountRegister) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        System.out.println(requestAccountRegister);
        requestAccountRegister.setPhoneNumber("Haven't set yet");
        accountService.saveAccount(requestAccountRegister);
        apiResponse.ok();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/editAdmin")
    public ResponseEntity<ApiResponse<ResponseAdmin>> editAdmin(@Valid @RequestBody RequestAccountEdit requestAccountEdit,
                                                                BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        if (bindingResult.hasErrors()) {
            apiResponse.ok(new ResponseAdmin());
            apiResponse.setMessage(bindingResult.getFieldError());
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (requestAccountEdit != null) {
            ResponseAccount responseAccount = accountService.saveAccount(requestAccountEdit);
            apiResponse.ok(responseAccount);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        }

        apiResponse.ok(new ResponseAdmin());
        apiResponse.setMessage("Cannot save because data is null");
        apiResponse.setStatus(StatusEnum.ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/findUserName")
    public ResponseEntity<ApiResponse<String>> findUserName( @RequestParam String username) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(accountService.findUsername(username));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/sendOTP")
    public ResponseEntity<ApiResponse<String>> sendOTP( @RequestParam String email) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(accountService.sendOtp(email));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/changeRoleAdmin")
    public ResponseEntity<String> changeRoleAdmin(@RequestParam(value = "user") int user,@RequestParam(value = "role") int role) throws Exception {
        System.out.println(user+" "+role);
        return new ResponseEntity<>(adminService.changeRoleAdmin(user,role), HttpStatus.OK);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<Integer>> dashboard() throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();

            apiResponse.ok(adminService.countNumberUserRegister());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping("/accessWebsite")
    public ResponseEntity<ApiResponse<Integer>> returnCountNumberAccessWebsite(@RequestParam("startDay") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDay,
                                                                               @RequestParam("endDay") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDay)  throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();

            apiResponse.ok(adminService.countNumberAccessWebsite(startDay,endDay));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping("/userLogin")
    public ResponseEntity<ApiResponse<UserLogin>> returnCountNumberUserLogin(@RequestParam("startDay") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDay,
                                                                             @RequestParam("endDay") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDay)  throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();

            apiResponse.ok(adminService.countNumberUserLogin(startDay,endDay));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping("/accessLog")
    public ResponseEntity<ApiResponse<UserLogin>> accessLog()  throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            adminService.increaseAccessWebsite();
            apiResponse.ok();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }


    //=========================Role===========================
    @GetMapping("/role/{id}")
    public ResponseEntity<ApiResponse<List<Role>>> returnRoleById(@PathVariable int id) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            Role role = adminService.findRoleById(id);
            apiResponse.ok(role);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping("/role")
    public ResponseEntity<ApiResponse<List<Role>>> returnRoles(@RequestParam(value = "name", required = false) String name) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(adminService.findAllRole(name));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/role")
    public ResponseEntity<ApiResponse<Role>> addRole(@Valid @RequestBody Role requestRole, BindingResult bindingResult) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();

            if (requestRole == null) {
                throw new NotFoundException("Cannot find Role to create");
            }

            Role role = adminService.findByRolename(requestRole.getName());
            if (role != null) {
                apiResponse.error("Name is exist");
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            apiResponse.ok(adminService.createRole(requestRole));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/role/editRole")
    public ResponseEntity<ApiResponse<Role>> editRole(@Valid @RequestBody Role requestRole, BindingResult bindingResult) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();

            //accountRegisterValidator.validateEdit(requestRole, bindingResult);
            int rs = adminService.checkRoleExisted(requestRole.getId(),requestRole.getName());
            if (rs > 0) {
                apiResponse.error("This name is exist");
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }

            apiResponse.ok(adminService.editRole(requestRole));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<ApiResponse> deleteRole(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Role role = adminService.findRoleById(id);
            if(accountRegisterValidator.validateDelete(role)){
                apiResponse.ok();
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            else{
                apiResponse.error("This role can not be deleted because it has been used by some account");
                return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
            }
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }
}
