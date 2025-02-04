package com.camrinInfoTech.ecrm.controller.validator;

import com.camrinInfoTech.ecrm.globalException.RequestValidationException;
import com.camrinInfoTech.ecrm.model.ErrorDetailModel;
import com.camrinInfoTech.ecrm.model.user;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDataValidator {

    // Main validation method
    public void validate(user user) throws RequestValidationException {
        List<ErrorDetailModel> errors = new ArrayList<>();

        // Validate fields of the user object
        errors.addAll(validateUserID(user.getUserID()));
        errors.addAll(validateUserName(user.getFirstName()));

        // If there are validation errors, throw a validation exception
        if (!errors.isEmpty()) {
            throwValidationException(errors);
        }
    }

    // Validate the user ID
    private List<ErrorDetailModel> validateUserID(Integer userID) {
        List<ErrorDetailModel> errors = new ArrayList<>();
        if (userID == null) {
            errors.add(createError("1001", "userID", "User ID should not be null"));
        }
        if (userID <= 0) {
            errors.add(createError("1002", "userID", "User ID should be a positive integer"));
        }
        return errors;
    }

    // Validate the user name
    private List<ErrorDetailModel> validateUserName(String userName) {
        List<ErrorDetailModel> errors = new ArrayList<>();
        if (userName == null || userName.trim().isEmpty()) {
            errors.add(createError("1003", "userName", "User name should not be null or empty"));
        }
        if (userName.length() < 3) {
            errors.add(createError("1004", "userName", "User name should be at least 3 characters long"));
        }
        return errors;
    }

    // Create an error detail model
    private ErrorDetailModel createError(String code, String field, String message) {
        ErrorDetailModel error = new ErrorDetailModel();
        error.setCode(code);
        error.setField(field);
        error.setMessage(message);
        return error;
    }

    // Throw validation exception with detailed error response
    private void throwValidationException(List<ErrorDetailModel> errors) throws RequestValidationException {
        Map<String, Object> errorResponse = new HashMap<>();
        List<Map<String, String>> errorMessages = new ArrayList<>();

        for (ErrorDetailModel error : errors) {
            Map<String, String> errorModel = new HashMap<>();
            errorModel.put("code", error.getCode());
            errorModel.put("field", error.getField());
            errorModel.put("message", error.getMessage());
            errorMessages.add(errorModel);
        }

        errorResponse.put("errors", errorMessages);
        ResponseEntity<Object> responseEntity = ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(errorResponse);
        throw new RequestValidationException(responseEntity);
    }
}
