package com.example.springrestful.service.AccountService;

import com.example.springrestful.model.entity.Account.Account;
import com.example.springrestful.model.entity.Account.AccountRole;
import com.example.springrestful.model.entity.UserLogin.UserLogin;
import com.example.springrestful.model.mapper.AccountMapper;
import com.example.springrestful.model.request.RequestAccount.RequestAccountChangePass;
import com.example.springrestful.model.request.RequestAccount.RequestAccountEdit;
import com.example.springrestful.model.request.RequestAccount.RequestAccountRegister;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import com.example.springrestful.repository.AccountRepository;
import com.example.springrestful.repository.AccountRoleRepository;
import com.example.springrestful.repository.UserLoginRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AccountRoleRepository accountRoleRepository;
    @Autowired
    UserLoginRepository userLoginRepository;

    @Override
    public ResponseAccount saveAccount(RequestAccountRegister account) throws Exception {
        String defaultSetMessage = "Haven't set yet";
        Account savedAccount = new Account();
        savedAccount.setUsername(account.getUsername());
        savedAccount.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
        savedAccount.setRole(account.getRole());
        savedAccount.setEmail(account.getEmail());
        savedAccount.setPhoneNumber(account.getPhoneNumber());
        savedAccount.setFullName(account.getFullName());
        savedAccount.setAddress(defaultSetMessage);
        savedAccount.setEducation(defaultSetMessage);
        savedAccount.setDescription(defaultSetMessage);
        savedAccount.setDateSignIn(new Date());
        savedAccount.setAvatarImg(defaultSetMessage);
        savedAccount.setIdentifyCardNumber(defaultSetMessage);
        savedAccount.setIdentifyCardName(defaultSetMessage);
        savedAccount.setBirthday(new Date());
        savedAccount.setRole_id(accountRoleRepository.findByRoleId(account.getRole_id()));

        return accountMapper.toResponseAccount(accountRepository.save(savedAccount));

    }

    @Override
    public ResponseAccount saveAccount(RequestAccountEdit account) throws Exception {
        Account savedAccount = accountRepository.findById(account.getId());
        savedAccount.setUsername(account.getUsername());
        // do not update password here
        savedAccount.setRole(account.getRole());
        savedAccount.setEmail(account.getEmail());
        savedAccount.setPhoneNumber(account.getPhoneNumber());
        savedAccount.setFullName(account.getFullName());
        savedAccount.setAddress(account.getAddress());
        savedAccount.setEducation(account.getEducation());
        savedAccount.setDescription(account.getDescription());
        savedAccount.setAvatarImg(account.getAvatarImg());
        savedAccount.setIdentifyCardNumber(account.getIdentifyCardNumber());
        savedAccount.setIdentifyCardName(account.getIdentifyCardName());
        savedAccount.setStatus(account.getStatus());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        savedAccount.setBirthday(dateFormat.parse(account.getBirthday()));
        savedAccount.setRole_id(accountRoleRepository.findByRoleId(account.getRole_id()));

        return accountMapper.toResponseAccount(accountRepository.save(savedAccount));
    }

    @Override
    public void removeAccountById(int id) throws Exception {
        accountRepository.deleteById(id);
    }

    @Override
    public List<ResponseAccount> findAllAccounts() throws Exception {
        return accountMapper.toResponseAccountList(accountRepository.findAll());
    }

    @Override
    public ResponseAccount findAccountById(int id) throws Exception {
        return accountMapper.toResponseAccount(accountRepository.findById(id));
    }

    @Override
    public ResponseAccount findByUsername(String username) throws Exception {
        return accountMapper.toResponseAccount(accountRepository.findByUsername(username));
    }
    @Override
    public List<Account> findAll() throws Exception {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findByRole(int role) throws Exception {
        //return accountRepository.findByRoleWithJPQL(role);
        return null;
    }
    @Override
    public Account findById(int id) throws Exception {
        return accountRepository.findById(id);
    }

    @Override
    public ResponseAccount loginAccount(String username, String password,int isAdmin) throws Exception {
        Account account = accountRepository.findByUsername(username);
        LocalDate currentDate = LocalDate.now();
        if(account != null ){
            boolean matches = BCrypt.checkpw(password,account.getPassword());
            if(matches) {
                if(isAdmin == 0){
                    if(userLoginRepository.countUserLoginsToday(account.getUsername(), currentDate) ==0){
                        UserLogin userLogin = new UserLogin();
                        userLogin.setUsername(account.getUsername());
                        userLogin.setLoginTime(LocalDateTime.now());
                        userLoginRepository.save(userLogin);
                    }
                }
                return accountMapper.toResponseAccount(account);
            }
        }
        return null;
    }

    @Override
    public String sendOtp(String email) {
        if(accountRepository.existsByEmail(email)){
            return "Email đã được sử dụng";
        }
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
        message.setText("Your OTP is: " + otp);
        javaMailSender.send(message);
        return otp.toString();
    }

    @Override
    public String findUsername(String username) throws Exception {
        boolean account = accountRepository.existsByUsername(username);
        if(account) return "Username đã tồn tại";
        return "";
    }

    @Override
    public boolean changePassword(RequestAccountChangePass requestAccountChangePass) {
        Account savedAccount = accountRepository.findById(requestAccountChangePass.getId());

        boolean matches = BCrypt.checkpw(requestAccountChangePass.getOldPass(),savedAccount.getPassword());
        if(!matches) return false;
        else{
            savedAccount.setPassword(BCrypt.hashpw(requestAccountChangePass.getNewPass(), BCrypt.gensalt()));
            accountRepository.save(savedAccount);
            return true;
        }
    }
}
