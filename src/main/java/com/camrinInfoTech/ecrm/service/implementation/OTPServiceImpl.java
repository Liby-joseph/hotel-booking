package com.camrinInfoTech.ecrm.service.implementation;

import com.camrinInfoTech.ecrm.constants.CommonEnum;
import com.camrinInfoTech.ecrm.entity.OTPInfo;
import com.camrinInfoTech.ecrm.entity.User;
import com.camrinInfoTech.ecrm.repository.OtpInfoRepository;
import com.camrinInfoTech.ecrm.repository.UserRepository;
import com.camrinInfoTech.ecrm.service.OTPService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message; // Correct import for Twilio Message
import com.twilio.type.PhoneNumber; // Correct import for Twilio PhoneNumber
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;
@Service
public class OTPServiceImpl implements OTPService {

  public static final String ACCOUNT_SID = "";
  public static final String AUTH_TOKEN  = "";
  public static final String TWILIO_PHONE_NUMBER = "";

    UserRepository userRepository;

    OtpInfoRepository otpInfoRepository;
    JavaMailSender mailSender;
    @Override
    public String generateOTP() {
        Random random = new Random();
        return String.format("%06d",random.nextInt(1000000));
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
    public void sentEmailOTP(String toEmail, String otp) {
        if (toEmail == null || toEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Recipient email cannot be null or empty");
        }

        Optional<User> user = userRepository.findByEmail(toEmail);
        if (user.isPresent()) {
            Long userId = user.get().getId();

            if (userId == null) {
                System.out.println("Invalid user");

            } else {
                String type = String.valueOf(CommonEnum.ENTROLLMENT_EMAIL_OTP);
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


    @Override
    public void sentPhoneOTP(String toPhone, String otp) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        // Create the SMS message
        Message message = Message.creator(
                new PhoneNumber("+xxx"), // To phone number
                new PhoneNumber("+xxx"), // From Twilio phone number
                "Your OTP for hotel reservation is: " + otp // SMS body
        ).create();

        System.out.println("OTP sent successfully! Message SID: " + message.getSid());
    }
}
