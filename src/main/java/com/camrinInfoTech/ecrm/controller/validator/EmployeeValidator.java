package com.camrinInfoTech.ecrm.controller.validator;

import com.camrinInfoTech.ecrm.globalException.RequestValidationException;
import com.camrinInfoTech.ecrm.model.Employee;
import com.camrinInfoTech.ecrm.model.ErrorDetailModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

import java.util.*;

@Component
public class EmployeeValidator {

    public void validateEmp(Employee employee) throws RequestValidationException {
        List<ErrorDetailModel> error = new ArrayList<>();
        error.addAll(validateEmpID(employee.getEmpID()));
        if(!error.isEmpty()){
            throwValidationException(error);
        }

    }

    public List<ErrorDetailModel> validateEmpID(Integer empID){
        List<ErrorDetailModel> error = new ArrayList<>();
        if (empID == null){
            error.add(addError("111","empID","empID null message"));
        }
        return error;
    }

    public ErrorDetailModel addError (String code,String field, String message ){
        ErrorDetailModel error = new ErrorDetailModel();
        error.setCode(code);
        error.setField(field);
        error.setMessage(message);
        return error;
    }

    public void throwValidationException(List<ErrorDetailModel> error) throws RequestValidationException {
        Map<String, Object> errorResponse = new HashMap<>();
        List<Map<String,String>> errorMessage = new ArrayList<>();

        for(ErrorDetailModel e : error){
            Map<String,String> errorModel = new HashMap<>();
            errorModel.put("code",e.getCode());
            errorModel.put("field",e.getField());
            errorModel.put("message", e.getMessage());
            errorMessage.add(errorModel);
        }

        errorResponse.put("Errors", errorMessage);
        ResponseEntity<Object> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        throw new RequestValidationException (responseEntity);
    }
}
