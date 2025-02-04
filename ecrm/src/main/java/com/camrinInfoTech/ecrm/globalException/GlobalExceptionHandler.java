package com.camrinInfoTech.ecrm.globalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<Object> handleRequestValidationException(RequestValidationException ex) {
        return ex.getResponseEntity();
    }
}