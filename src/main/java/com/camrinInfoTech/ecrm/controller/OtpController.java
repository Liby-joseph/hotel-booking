package com.camrinInfoTech.ecrm.controller;

import com.camrinInfoTech.ecrm.entity.OTPRequestBody;
import com.camrinInfoTech.ecrm.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("otp")
public class OtpController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendOTP(@RequestBody OTPRequestBody e) {

        String otp = emailService.generateOTP();
        emailService.sentOTP(e.getEmail(), otp);
        return "OTP sent to " + e.getEmail();
    }
}
