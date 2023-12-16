package com.example.springrestful.service.AuthService;

import com.example.springrestful.model.request.RequestAccount.RequestAccountEdit;
import com.example.springrestful.model.request.RequestAccount.RequestAccountLogin;
import com.example.springrestful.model.request.RequestAccount.RequestAccountRegister;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
public interface AuthService {
    ResponseAccount register (RequestAccountRegister requestAccount) throws Exception;
    ResponseAccount login (RequestAccountLogin requestAccount) throws Exception;
    ResponseAccount recoverPassword () throws Exception;
    ResponseAccount loginWithGoogle () throws Exception;
    ResponseAccount loginWithFaceBook () throws Exception;
    ResponseAccount authenticateWithEmail (String email, RequestAccountEdit requestAccountEdit) throws Exception;
}
