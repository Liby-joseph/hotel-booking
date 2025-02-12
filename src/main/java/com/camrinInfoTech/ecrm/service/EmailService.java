package com.camrinInfoTech.ecrm.service;


public interface EmailService {
    public String generateOTP();
    public void sentOTP (String toEmail, String otp);


}
