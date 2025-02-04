package com.camrinInfoTech.ecrm.service.implementation;

import com.camrinInfoTech.ecrm.constants.CommonEnum;
import com.camrinInfoTech.ecrm.entity.OTPInfo;
import com.camrinInfoTech.ecrm.entity.User;
import com.camrinInfoTech.ecrm.repository.OtpInfoRepository;
import com.camrinInfoTech.ecrm.repository.UserRepository;
import com.camrinInfoTech.ecrm.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OtpInfoRepository otpInfoRepository;
    @Autowired
    JavaMailSender mailSender;
    String type = String.valueOf(CommonEnum.ENTROLLMENT_EMAIL_OTP);


    @Override
    public String generateOTP() {

        Random random = new Random();
        return String.format("%06d", random.nextInt(100000));
    }

    public void sentMail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail.trim());
        message.setSubject("Your OTP Code");
        message.setText("Your OTP Code is: " + otp + ". It is valid for 5 minutes.");


        try {
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    @Override
    public void sentOTP(String toEmail, String otp) {
        if (toEmail == null || toEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Recipient email cannot be null or empty");
        }

        Optional<User> user = userRepository.findByEmail(toEmail);
        if (user.isPresent()) {
            Long userId = user.get().getId();

            if (userId == null) {
                System.out.println("Invalid user");

            } else {
                OTPInfo otpInfo = otpInfoRepository.findByTypeAndUserId(type, user.get().getId());
                if (otpInfo == null) {
                    sentMail(toEmail, otp);
                    OTPInfo newOTPInfo = new OTPInfo();
                    newOTPInfo.setOtp(otp);
                    newOTPInfo.setUserId(userId);
                    newOTPInfo.setType(type);
                    newOTPInfo.setLastModifiedDate(new Date());
                    newOTPInfo.setNoOfAttempts(1);
                    otpInfoRepository.save(newOTPInfo);

                } else {
                    if (otpInfo.getNoOfAttempts() < 10) {
                        sentMail(toEmail, otp);
                        otpInfo.setOtp(otp);
                        otpInfo.setLastModifiedDate(new Date());
                        otpInfo.setNoOfAttempts(otpInfo.getNoOfAttempts() + 1);
                        otpInfoRepository.save(otpInfo);
                    } else {
                        System.err.println("Error sending email: No of attempt exceeded ");
                    }
                }
            }


        } else {
            System.out.println("User not present "); // return BAD request
        }

    }

}
