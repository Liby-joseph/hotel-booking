package com.camrinInfoTech.ecrm.service;

import com.camrinInfoTech.ecrm.model.Employees;
import com.camrinInfoTech.ecrm.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    public List<Employees> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public Employees saveEmployee(Employees e){
        return employeeRepository.save(e);
    }
}
