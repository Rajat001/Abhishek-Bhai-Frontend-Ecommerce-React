package com.dev.controller;


import com.dev.domain.USER_ROLE;
import com.dev.model.User;
import com.dev.repository.UserRepository;
import com.dev.response.AuthResponse;
import com.dev.response.SignupRequest;
import com.dev.service.AuthService;
import com.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/users/profile")
    public ResponseEntity<User> createUserHandler(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        return ResponseEntity.ok(user);
    }
}
