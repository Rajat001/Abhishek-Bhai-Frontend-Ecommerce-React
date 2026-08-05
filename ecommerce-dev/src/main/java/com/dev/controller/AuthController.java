package com.dev.controller;


import com.dev.domain.USER_ROLE;
import com.dev.model.User;
import com.dev.model.VerificationCode;
import com.dev.repository.UserRepository;
import com.dev.repository.VerificationCodeRepository;
import com.dev.request.LoginOtpRequest;
import com.dev.request.LoginRequest;
import com.dev.response.ApiResponse;
import com.dev.response.AuthResponse;
import com.dev.response.SignupRequest;
import com.dev.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor // For this private final UserRepository userRepository , we need to add this annotation

public class AuthController {
    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {

        String jwt = authService.createUser(req);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register success");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(
            @RequestBody LoginOtpRequest req) throws Exception {

        authService.sentLoginOtp(req.getEmail() , req.getRole());

        ApiResponse res = new ApiResponse();

        res.setMessage("OTP send Successfully");

        return ResponseEntity.ok(res);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {

        AuthResponse authResponse = authService.siging(req);

        return ResponseEntity.ok(authResponse);
    }
}
