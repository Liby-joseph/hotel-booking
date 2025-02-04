package com.camrinInfoTech.ecrm.controller;

import com.camrinInfoTech.ecrm.entity.OTPInfo;
import com.camrinInfoTech.ecrm.entity.User;
import com.camrinInfoTech.ecrm.repository.OtpInfoRepository;
import com.camrinInfoTech.ecrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController

@RequestMapping ("otp")
public class ValidateOTP {


    @Autowired
    UserRepository userRepository;

    @Autowired
    OtpInfoRepository otpInfoRepository;
    String email ;
    String OTP;
    String verificationType;
    Map<String , String> OTPdetails = new HashMap<>();

    @GetMapping("/validateOTP")
    public void validateOTP (@RequestParam Map<String , String> otp){
        OTPdetails = otp;

        email = OTPdetails.get("email");
        OTP = OTPdetails.get("otp");
        verificationType = OTPdetails.get("type");

        Optional<User>  optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.get();
        OTPInfo otpInfo  = otpInfoRepository.findByTypeAndUserId(verificationType,user.getId());
        String originalOTP = otpInfo.getOtp();

        if(originalOTP.equals(OTP)){

            System.out.println("OTP Validation success");
            user.setEmailVerified(true);
            userRepository.save(user);
        }
        else {
            System.out.println("OTP Validation unsuccess");
            int noofattempt = otpInfo.getNoOfAttempts();
            otpInfo.setNoOfAttempts(noofattempt+1);
            otpInfoRepository.save(otpInfo);
        }

    }
}
