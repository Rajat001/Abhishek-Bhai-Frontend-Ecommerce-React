package com.dev.service.impl;

import com.dev.config.JwtProvider;
import com.dev.domain.USER_ROLE;
import com.dev.model.Cart;
import com.dev.model.Seller;
import com.dev.model.User;
import com.dev.model.VerificationCode;
import com.dev.repository.CartRepository;
import com.dev.repository.SellerRepository;
import com.dev.repository.UserRepository;
import com.dev.repository.VerificationCodeRepository;
import com.dev.request.LoginRequest;
import com.dev.response.AuthResponse;
import com.dev.response.SignupRequest;
import com.dev.service.AuthService;
import com.dev.service.EmailService;
import com.dev.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomUserServiceImp customUserService;
    private final SellerRepository sellerRepository;

    //    @Autowired
    //    private JwtProvider jwtProvider;

    @Override
    public void sentLoginOtp(String email , USER_ROLE role) throws Exception {
            String SIGNING_PREFIX = "signing_";


//            if(email.startsWith(SIGNING_PREFIX)){
//                email =email.substring(SIGNING_PREFIX.length());
//
//                User user = userRepository.findByEmail(email);
//                if(user == null){
//                    throw new Exception("user not exist with provided email");
//                }
//            }

            if(email.startsWith(SIGNING_PREFIX)){
                email =email.substring(SIGNING_PREFIX.length());

                if(role.equals(USER_ROLE.ROLE_SELLER)){
                    Seller seller = sellerRepository.findByEmail(email);
                    if(seller == null){
                        throw new Exception("seller not found");
                    }
                }
                else
                {
                    System.out.println("Email " + email);
                    User user = userRepository.findByEmail(email);
                    if(user == null){
                        throw new Exception("User not exist with provided email !!!");
                    }
                }
            }

            VerificationCode isExist = verificationCodeRepository.findByEmail(email);

            if(isExist!=null){
                verificationCodeRepository.delete(isExist);
            }

            String otp = OtpUtil.generateOtp();

            VerificationCode verificationCode = new VerificationCode();
            verificationCode.setOtp(otp);
            verificationCode.setEmail(email);
            verificationCodeRepository.save(verificationCode);

            String subject = "Ecommerce Site for Login / SignUp Via Otp";
            String text = "Your Login/SignUp OTP is " + otp;
            emailService.sendVerificationOtpEmail(email,otp,subject,text);

    }

    @Override
    public String createUser(SignupRequest req) throws Exception {

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());

        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())){
            throw new Exception("wrong Otp ...");
        }

        User user = userRepository.findByEmail(req.getEmail());

        if(user==null){
            User createUser = new User();
            createUser.setEmail(req.getEmail());
            createUser.setFullName(req.getFullName());
            createUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createUser.setMobile("9711461442");
            createUser.setPassword(passwordEncoder.encode(req.getOtp()));
            user= userRepository.save(createUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

    @Override
    public AuthResponse siging(LoginRequest req) throws Exception {
        String username = req.getEmail();
        String otp = req.getOtp();

        Authentication authentication = authenticate(username,otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Success");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        authResponse.setRole(USER_ROLE.valueOf(roleName));
        return authResponse;
    }

    private Authentication authenticate(String username , String otp) throws Exception {
        UserDetails userDetails = customUserService.loadUserByUsername(username);

        String SELLER_PREFIX = "seller_";

if (username.startsWith(SELLER_PREFIX))
{
    username = username.substring(SELLER_PREFIX.length());
}

        if(userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);

        if(verificationCode == null || !verificationCode.getOtp().equals(otp)){
            throw new Exception("Wrong otp");
        }
        return new UsernamePasswordAuthenticationToken(userDetails ,
                                                    null ,
                                                       userDetails.getAuthorities());
    }
}










