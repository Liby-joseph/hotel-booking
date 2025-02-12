package com.camrinInfoTech.ecrm.repository;

import com.camrinInfoTech.ecrm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String s);
    Optional<User> findByPhno(String s);

    Optional<User> findByEmailAndPhno(String e, String p);
}
