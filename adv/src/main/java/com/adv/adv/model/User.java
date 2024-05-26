package com.adv.adv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

    @Entity
    public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "UserName cannot be null or empty")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "UserName cannot contain special characters")
    private String username;
    @NotBlank(message = "Email cannot be null or empty")
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "Address can not be empty")
    private String address;

    @NotBlank(message = "Phone can not be empty")
    @Pattern(regexp = "^\\d{11}$", message = "Phone number must be exactly 11 digits")
    private String phone;

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @NotBlank(message = "Password cannot be null or empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespaces")
    private String password;
  //  @NotBlank(message = "Confirm password cannot be null or empty")
    @Transient
    private String confirmPassword;



    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public enum UserType {
        USER, ADMIN
    }
    public User() {
    }

    
    public User(long id, String username, String email, String password, String address, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address=address;
        this.phone=phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  public String getConfirmPassword() {
    return this.confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

    

}
