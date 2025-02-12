package com.camrinInfoTech.ecrm.controller;

import com.camrinInfoTech.ecrm.model.Employees;
import com.camrinInfoTech.ecrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeesController {

    @Autowired
    public EmployeeService es;

    @GetMapping
    public List<Employees> getAllEmployee() {
        return es.getAllEmployee();
    }

    @PostMapping
    public Employees createEmployee(@RequestBody Employees e) {
        return es.saveEmployee(e);
    }

}
