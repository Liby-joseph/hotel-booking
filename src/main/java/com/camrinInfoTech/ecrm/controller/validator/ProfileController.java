package com.camrinInfoTech.ecrm.controller.validator;

import com.camrinInfoTech.ecrm.entity.User;
import com.camrinInfoTech.ecrm.repository.UserRepository;
import com.camrinInfoTech.ecrm.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class ProfileController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/get-profile-data")
    public ResponseEntity getUserProfileData(@RequestHeader("Authorization") String authToken){
        if((authToken == null ) || (!authToken.startsWith("Bearer "))){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid auth token");
        }
        Claims claims = JWTUtil.validateToken(authToken.substring(7));

        Date Expiry = claims.getExpiration();
        if(Expiry.before(new Date())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("IToken expired");
        }
        String email =  claims.getSubject();
        Optional<User> user = userRepository.findByEmail(email);
        return ResponseEntity.ok(user);
    }
}
