package com.camrinInfoTech.ecrm.controller;


import com.camrinInfoTech.ecrm.controller.validator.UserDataValidator;
import com.camrinInfoTech.ecrm.globalException.RequestValidationException;
import com.camrinInfoTech.ecrm.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDataValidator usersValidator;

    @PostMapping("/getInfo")
    public String getUserInfo(@RequestBody user us) throws RequestValidationException {
        usersValidator.validate(us);
        return "Validation successful!";
    }
}
