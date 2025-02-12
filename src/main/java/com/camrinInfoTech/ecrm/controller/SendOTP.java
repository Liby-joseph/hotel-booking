package com.camrinInfoTech.ecrm.controller;

import com.camrinInfoTech.ecrm.constants.CommonEnum;
import com.camrinInfoTech.ecrm.entity.RequestBodyOTP;
import com.camrinInfoTech.ecrm.service.EmailService;
import com.camrinInfoTech.ecrm.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping ("userEntrollment/otp")
public class SendOTP {
    @Autowired
    OTPService otpService;
    @PostMapping ("/send")
    public void sendUserEntrollmentOTP(@RequestBody RequestBodyOTP requestBodyOTP){

        String emailType = String.valueOf(CommonEnum.ENTROLLMENT_EMAIL_OTP);
        String phoneType = String.valueOf(CommonEnum.ENTROLLMENT_PHONE_OTP);

        String type = requestBodyOTP.getType();
        if (type .equals(emailType)){
            String otp = otpService.generateOTP();
            otpService.sentEmailOTP(requestBodyOTP.getValue(),otp);
        }
        else // if(type.equals(phoneType))
            {
            String otp = otpService.generateOTP();
            otpService.sentPhoneOTP(requestBodyOTP.getValue(),otp);
        }

    }
}
