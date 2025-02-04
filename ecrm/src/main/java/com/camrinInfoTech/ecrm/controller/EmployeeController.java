package com.camrinInfoTech.ecrm.controller;

import com.camrinInfoTech.ecrm.controller.validator.EmployeeValidator;
import com.camrinInfoTech.ecrm.globalException.RequestValidationException;
import com.camrinInfoTech.ecrm.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")

public class EmployeeController {
    @Autowired
    EmployeeValidator ev;
    @PostMapping("/getdata")
    public String getEmployeeData(@RequestBody Employee emp) throws RequestValidationException
    {
        ev.validateEmp(emp);
        return "Employee created "+emp.getEmpID();
    }
}
