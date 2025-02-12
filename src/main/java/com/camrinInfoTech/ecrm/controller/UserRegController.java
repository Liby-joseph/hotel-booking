package com.camrinInfoTech.ecrm.controller;

import com.camrinInfoTech.ecrm.entity.User;
import com.camrinInfoTech.ecrm.model.UserEnrollmentRequest;
import com.camrinInfoTech.ecrm.service.UserService;
import com.camrinInfoTech.ecrm.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/hotel")

public class UserRegController {
    @Autowired
private UserService us;


    @GetMapping("/dedup")
    public ResponseEntity<?>  checkDuplicate (@RequestParam String Type, @RequestParam String Value) {
        try {
            boolean exists = us.IsDuplicate(Type, Value);
            if (exists) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
            }
            String TokenData = Type +":"+ Value;
            String EncTokenData = AESUtil.encrypt(TokenData);
            String ResponseType = Type.equalsIgnoreCase("Phone")?"phoneToken":"emailToken";
            return ResponseEntity.ok().body(Map.of(ResponseType, EncTokenData));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/enrollment")
    public ResponseEntity<?> userEnrollment(@RequestBody UserEnrollmentRequest ue) {
        try{
            String emailToken = AESUtil.decrypt(ue.getEmailToken());
            String[] emailTokenData = emailToken.split(":");
            String phoneToken = AESUtil.decrypt(ue.getPhoneToken());
            String[] phoneTokenData = phoneToken.split(":");
            if ((phoneTokenData.length != 2) || (emailTokenData.length != 2)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Token");
            }

            String Email = emailTokenData[1];
            String Phone = phoneTokenData[1];
            if ((!Email.equals(ue.getEmail())) || (!Phone.equals(ue.getPhno()))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token validation failed");
            }
            User u = new User(null, ue.getFirstName(),ue.getLastName(), ue.getEmail(), ue.getPhno());
            User savedUser = us.SaveUser(u);
            return ResponseEntity.status(HttpStatus.CREATED).body("User enrolled successfully.");

    }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error enrolling user.");
        }
    }

}
