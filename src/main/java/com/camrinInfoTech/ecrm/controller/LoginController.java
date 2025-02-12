package com.camrinInfoTech.ecrm.controller;

import com.camrinInfoTech.ecrm.entity.LoginInfo;
import com.camrinInfoTech.ecrm.entity.User;
import com.camrinInfoTech.ecrm.repository.OtpInfoRepository;
import com.camrinInfoTech.ecrm.repository.UserRepository;
import com.camrinInfoTech.ecrm.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @PostMapping("/user")
    public String userLogin(@RequestBody LoginInfo l){
        String email = l.getEmail();
        String phone = l.getphone();
        Optional<User> user = userRepository.findByEmailAndPhno(email,phone);
        String loginToken = "";
        if(user.isEmpty()){
            System.out.println("Invalid login");

        }else{

            if(user.get().getEmailVerified() == false){
                System.out.println("Invalid login");
            } else if (user.get().getPhoneVerified() == false) {
                System.out.println("Invalid login");
            }else {
                loginToken = JWTUtil.generateToken(email);
                System.out.println("Login Sucess");
            }

        }
        return loginToken;
    }

}
