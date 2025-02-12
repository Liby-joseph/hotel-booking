package com.camrinInfoTech.ecrm.globalException;


import org.springframework.http.ResponseEntity;

public class RequestValidationException extends Exception {

    private final ResponseEntity<Object> responseEntity;

    public RequestValidationException(ResponseEntity<Object> responseEntity) {
        super("Request validation failed");
        this.responseEntity = responseEntity;
    }

    public ResponseEntity<Object> getResponseEntity() {
        return responseEntity;
    }
}
