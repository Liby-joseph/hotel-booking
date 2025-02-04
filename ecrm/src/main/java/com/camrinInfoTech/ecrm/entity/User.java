package com.camrinInfoTech.ecrm.entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private  Long id;
    @NonNull
    private String firstName;

    @NonNull
    private  String lastName;

    @NonNull
    //@Email
    @Column(unique = true)
    private String email;

    @NonNull
    @Column(unique = true)
    private  String phno;

    private Boolean isEmailVerified;

    private Boolean isPhoneVerified;

    public User(Long id, @NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String phno, Boolean isEmailVerified, Boolean isPhoneVerified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phno = phno;
        this.isEmailVerified = isEmailVerified;
        this.isPhoneVerified = isPhoneVerified;
    }

    public User(Long id, @NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String phno) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phno = phno;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }
}
