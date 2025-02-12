package com.camrinInfoTech.ecrm.service;

import com.camrinInfoTech.ecrm.entity.User;
import com.camrinInfoTech.ecrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository ur;
    public User SaveUser(User user){
        return ur.save(user);
    }

    public boolean IsDuplicate (String Type, String Value) {
        if ("Email".equalsIgnoreCase(Type)) {
            return ur.findByEmail(Value).isPresent();
        } else if ("Phone".equalsIgnoreCase(Type)) {
            return ur.findByPhno(Value).isPresent();
        }
        throw new IllegalArgumentException("Invalid Type: " + Type);
    }
}
