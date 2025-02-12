package com.camrinInfoTech.ecrm.service;

public interface OTPService  {

    public String generateOTP();
    public void sentEmailOTP (String toEmail, String otp);

    public void sentPhoneOTP (String toPhone, String otp);
}
