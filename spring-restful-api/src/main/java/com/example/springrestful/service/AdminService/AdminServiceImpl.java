package com.example.springrestful.service.AdminService;


import com.example.springrestful.model.entity.AccessLog.AccessLog;
import com.example.springrestful.model.entity.Account.Account;
import com.example.springrestful.model.entity.Admin.Admin;
import com.example.springrestful.model.entity.Role.Role;
import com.example.springrestful.model.mapper.AccountMapper;
import com.example.springrestful.model.mapper.AdminMapper;
import com.example.springrestful.model.request.RequestAdmin.RequestAdmin;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springrestful.model.response.ResponseAdmin.ResponseCountUserLogin;
import com.example.springrestful.repository.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;
import com.example.springwebapp.model.response.ResponseDashboard.ResponseDashboard;
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AccountRoleRepository accountRoleRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccessLogRepository accessLogRepository;
    @Autowired
    UserLoginRepository userLoginRepository;

    @Override
    public void increaseAccessWebsite() throws Exception {
        Date currentDate = new Date();
        Optional<AccessLog> accessLog = Optional.ofNullable(accessLogRepository.findByDate(currentDate));

        AccessLog increaseAccess;
        if (accessLog.isPresent()) {
            increaseAccess = accessLog.get();
            increaseAccess.setPageViews(increaseAccess.getPageViews() + 1);
        } else {
            increaseAccess = new AccessLog();
            increaseAccess.setDate(currentDate);
            increaseAccess.setPageViews(1);
        }

        accessLogRepository.save(increaseAccess);
    }

    @Override
    public List<AccessLog> countNumberAccessWebsite(Date startDate, Date endDate) throws Exception {
        return accessLogRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public List<Integer> countNumberUserRegister() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(adminRepository.countNumberUserRegister());
        list.add(adminRepository.countNumberRecruiterRegister());
        list.add(adminRepository.countNumberCandidateRegister());
        return list;
    }

    @Override
    public List<ResponseCountUserLogin> countNumberUserLogin(LocalDate startDate, LocalDate endDate) throws Exception {
        List<ResponseCountUserLogin> list = new ArrayList<>();
        List<Object[]> listData = userLoginRepository.findByLoginTimeBetween(startDate,endDate);
        for (Object[] data : listData) {
            LocalDate date = LocalDate.parse(data[0].toString());
            int count = Integer.parseInt(data[1].toString());
            ResponseCountUserLogin response = new ResponseCountUserLogin(date, count);
            list.add(response);
        }
        return list;
    }

    @Override
    public int countNumberTransactionsSuccessful() throws Exception {
        return 0;
    }

    @Override
    public int countMostPopularJob() throws Exception {
        return 0;
    }

    @Override
    public int countMostPopularRecruitment() throws Exception {
        return 0;
    }

    @Override
    public int countMostPopularSkill() throws Exception {
        return 0;
    }

    @Override
    public int countMostPopularCategory() throws Exception {
        return 0;
    }
    //==========================Admin==========================
    @Override
    public Admin findById(int id) throws Exception {
        return adminRepository.findById(id);
    }

    @Override
    public List<Admin> findAll() throws Exception {
        return adminRepository.findAll();
    }

    @Override
    public List<Admin> findByAccount(int accountId)throws Exception {
//        return adminMapper.toResponseAdminList(adminRepository.findByAccountWithJPQL(accountId));
        return adminRepository.findByAccountWithJPQL(accountId);
    }

    @Override
    public ResponseAdmin save(RequestAdmin admin) {
        Account account = accountRepository.findById(admin.getAccountId());
        if (account!=null){
            Admin saveAdmin = new Admin();
            saveAdmin.setId(admin.getId());
            saveAdmin.setDescription(admin.getDescription());
            saveAdmin.setRole(admin.getRole());
            saveAdmin.setMajor(admin.getMajor());
            saveAdmin.setStatus(admin.getStatus());
            saveAdmin.setExperienceYear(admin.getExperienceYear());
           // saveAdmin.setAccountAdmin(account);


            return adminMapper.toResponseAdmin(adminRepository.save(saveAdmin));
        }
        return null;
    }

    @Override
    public void removeById(int id) throws Exception {

        adminRepository.deleteById(id);
    }

    //===========================Role==================================
    @Override
    public Role createRole(Role role) throws Exception {
        Role r = new Role();
        r.setName(role.getName());
        r.setAdmin_manage(role.getAdmin_manage());
        r.setAccount_manage(role.getAccount_manage());
        r.setRole_manage(role.getRole_manage());
        return accountRoleRepository.save(r);
    }

    @Override
    public Role editRole(Role role) throws Exception {
        Role r = accountRoleRepository.findByRoleId(role.getId());
        r.setName(role.getName());
        r.setAdmin_manage(role.getAdmin_manage());
        r.setAccount_manage(role.getAccount_manage());
        r.setRole_manage(role.getRole_manage());
        return accountRoleRepository.save(r);
    }

    @Override
    public void removeRole(int id) throws Exception {
        accountRoleRepository.deleteById(id);
    }

    @Override
    public List<Role> findAllRole(String name) throws Exception {
        System.out.println(name);
        if(name == null || name.isEmpty()) return accountRoleRepository.findAll();
        else
            return accountRoleRepository.searchRoleName(name);
    }

    @Override
    public Role findRoleById(int id) throws Exception {
        return accountRoleRepository.findByRoleId(id);
    }

    @Override
    public Role findByRolename(String name) throws Exception {
        return accountRoleRepository.findByName(name);
    }

    @Override
    public int checkRoleExisted(int id, String name) throws Exception {
        List<Role> checkRoleExisted = accountRoleRepository.checkRoleExisted(name,id);
        return checkRoleExisted.size();
    }

    @Override
    public int countAccountOfRole(int id) throws Exception{
        return accountRoleRepository.countAccountOfRole(id);
    }

    @Override
    public List<ResponseAccount> findAllUser(String userName, int page) throws Exception {
        List<Account> list = null;
        if(userName.isEmpty() || userName == null) list = accountRepository.getAllUser();
        else list = accountRepository.getAllUser(userName);
        return accountMapper.toResponseAccountList(list);
    }

    @Override
    public void changeStatus(int id) throws Exception {
        accountRepository.changeStatus(id);
    }

    @Override
    public ResponseAccount getAccountByUserName(String username) throws Exception {
        return accountMapper.toResponseAccount(accountRepository.findByUsername(username));
    }

    @Override
    public List<ResponseAccount> findAllAdmin(String userName, int page) throws Exception {
        List<Account> list = null;
        if(userName.isEmpty() || userName == null) list = accountRepository.getAllAdmin();
        else list = accountRepository.getAllAdmin(userName);
        return accountMapper.toResponseAccountList(list);
    }

    @Override
    public String changeRoleAdmin(int user, int role) throws Exception {
        Role r = accountRoleRepository.findByRoleId(role);
        adminRepository.changeRoleAdmin(user,r);
        return "ok";
    }

    @Override
    public void changePassword(String email, String password) {
        accountRepository.changePassword(email, BCrypt.hashpw(password, BCrypt.gensalt()));
    }

    @Override
    public String sendOTPToGetPassword(String email) {
        Account account = accountRepository.findByEmail(email);

        String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int OTP_LENGTH = 6;
        StringBuilder otp = new StringBuilder();

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            otp.append(CHARACTERS.charAt(index));
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP Verification");
        message.setText("Your OTP is: " + otp + " and your Username is: "+account.getUsername());
        javaMailSender.send(message);
        System.out.println(otp.toString());
        return otp.toString();
    }

    @Override
    public ResponseDashboard countStatistics() {
        ResponseDashboard res = new ResponseDashboard();
        res.setCountUsers(adminRepository.countNumberUserRegister());
        res.setCountUser(adminRepository.countNumberCandidateRegister());
        res.setCountRecruiters(adminRepository.countNumberRecruiterRegister());
        res.setCountJobs(adminRepository.countNumberJobs());
        return res;
    }
}
