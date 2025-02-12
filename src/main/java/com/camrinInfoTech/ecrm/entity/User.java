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

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPhno() {
        return phno;
    }

    public void setPhno(@NonNull String phno) {
        this.phno = phno;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public Boolean getPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(Boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    public Long getId() {
        return id;
    }
}
