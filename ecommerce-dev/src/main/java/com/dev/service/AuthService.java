package com.dev.service;

import com.dev.domain.USER_ROLE;
import com.dev.request.LoginRequest;
import com.dev.response.AuthResponse;
import com.dev.response.SignupRequest;

public interface AuthService {
    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse siging(LoginRequest req) throws Exception;
}
