package com.dev.controller;


import com.dev.domain.AccountStatus;
import com.dev.exceptions.SellerException;
import com.dev.model.Seller;
import com.dev.model.SellerReport;
import com.dev.model.VerificationCode;
import com.dev.repository.VerificationCodeRepository;
import com.dev.request.LoginRequest;
import com.dev.response.AuthResponse;
import com.dev.service.AuthService;
import com.dev.service.EmailService;
import com.dev.service.SellerReportService;
import com.dev.service.SellerService;
import com.dev.utils.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {

        private final SellerService sellerService;
        private final VerificationCodeRepository verificationCodeRepository;
        private final AuthService authService;
        private final EmailService emailService;
        private final SellerReportService sellerReportService;

        @PostMapping("/login")
        public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest req) throws Exception {
            String otp = req.getOtp();
            String email = req.getEmail();
            req.setEmail("seller_"+email);
            AuthResponse authResponse = authService.siging(req);
            return ResponseEntity.ok(authResponse);
        }

        @PatchMapping("/verify/{otp}")
        public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception{
            VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);

            if(verificationCode == null || !verificationCode.getOtp().equals(otp)){
                throw new Exception("Wrong Otp 1...");
            }

            Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);

            return new ResponseEntity<>(seller, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Seller> createSeller(
                @RequestBody Seller seller) throws Exception , MessagingException{

            Seller savedSeller = sellerService.createSeller(seller);

            String otp = OtpUtil.generateOtp();

            VerificationCode verificationCode = new VerificationCode();
            verificationCode.setOtp(otp);
            verificationCode.setEmail(seller.getEmail());
            verificationCodeRepository.save(verificationCode);

//          VerificationCode verificationCode = verificationService.createVerification(otp , seller.getEmail());

            String subject = "Ecommerce Site Fashion , Email Verification Code";
            String text = "Welcome to Ecommerce Site , Verify your Account using this link";
            String frontend_url = "http:..localhost:8888/verify-seller/";
            emailService.sendVerificationOtpEmail(seller.getEmail(), verificationCode.getOtp() , subject , text + frontend_url);
            return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Seller> getSellerById(@PathVariable Long id)
            throws SellerException{
            Seller seller = sellerService.getSellerById(id);
            return new ResponseEntity<>(seller , HttpStatus.OK);
        }


        @GetMapping("/profile")
        public ResponseEntity<Seller> getSellerByJwt(
                @RequestHeader("Authorization") String jwt) throws Exception{
            Seller seller = sellerService.getSellerProfile(jwt);
            return new ResponseEntity<>(seller, HttpStatus.OK);
        }


        @GetMapping("/report")
        public ResponseEntity<SellerReport> getSellerReport(
                @RequestHeader("Authorization") String jwt) throws Exception{
//            String email = jwtProvider.getEmailFromJwtToken(jwt);
            Seller seller = sellerService.getSellerProfile(jwt);
            SellerReport report = sellerReportService.getSellerReport(seller);
            return new ResponseEntity<>(report , HttpStatus.OK);
        }

        @GetMapping
        public ResponseEntity<List<Seller>> getAllSellers(
                @RequestParam(required=false)AccountStatus status){
            List<Seller> sellers = sellerService.getAllSellers(status);
            return ResponseEntity.ok(sellers);
        }

        @PatchMapping()
        public ResponseEntity<Seller> updateSeller(
                @RequestHeader("Authorization") String jwt ,
                @RequestBody Seller seller) throws Exception{

            Seller profile = sellerService.getSellerProfile(jwt);
            Seller updateSeller = sellerService.updateSeller(profile.getId(), seller);
            return ResponseEntity.ok(updateSeller);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception{
            sellerService.deleteSeller(id);
            return ResponseEntity.noContent().build();
        }
}
