package com.camrinInfoTech.ecrm.model;

import java.util.Date;

public class Employee {
    private Integer empID;
    private String name;
    private String empCode;

    private Date JoiningDate;

    private String email;
    private String contactNo;

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public Date getJoiningDate() {
        return JoiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        JoiningDate = joiningDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee(Integer empID, String name, String empCode, Date joiningDate, String email, String contactNo) {
        this.empID = empID;
        this.name = name;
        this.empCode = empCode;
        JoiningDate = joiningDate;
        this.email = email;
        this.contactNo = contactNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
