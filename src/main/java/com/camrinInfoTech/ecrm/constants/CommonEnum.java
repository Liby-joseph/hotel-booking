package com.camrinInfoTech.ecrm.constants;

public enum CommonEnum {
    ENTROLLMENT_EMAIL_OTP("emailOTP"),ENTROLLMENT_PHONE_OTP("phoneOTP");


    private final String value;

    CommonEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
