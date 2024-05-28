package com.adv.adv.model;

import com.adv.adv.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Username cannot be null or empty", groups = {ValidationGroups.CreateProfile.class, ValidationGroups.UpdateProfile.class})
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Username cannot contain special characters", groups = {ValidationGroups.CreateProfile.class, ValidationGroups.UpdateProfile.class})
    private String username;

    @NotBlank(message = "Email cannot be null or empty", groups = {ValidationGroups.CreateProfile.class, ValidationGroups.UpdateProfile.class})
    @Email(message = "Email must be valid", groups = {ValidationGroups.CreateProfile.class, ValidationGroups.UpdateProfile.class})
    private String email;

    @NotBlank(message = "Address cannot be empty", groups = ValidationGroups.CreateProfile.class)
    private String address;

    @NotBlank(message = "Phone cannot be empty", groups = ValidationGroups.CreateProfile.class)
    @Pattern(regexp = "^\\d{11}$", message = "Phone number must be exactly 11 digits", groups = ValidationGroups.CreateProfile.class)
    private String phone;

    @NotBlank(message = "Password cannot be null or empty", groups = {ValidationGroups.CreateProfile.class, ValidationGroups.UpdateProfile.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespaces",
            groups = {ValidationGroups.CreateProfile.class, ValidationGroups.UpdateProfile.class})
    private String password;

    @Transient
    private String confirmPassword;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public enum UserType {
        USER, ADMIN
    }
  public User() {
  }

  public User(long id, String username, String email, String address, String phone, String password, String confirmPassword) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.address = address;
    this.phone = phone;
    this.password = password;
    this.confirmPassword = confirmPassword;
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

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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

  public UserType getUserType() {
    return userType;
}

public void setUserType(UserType userType) {
    this.userType = userType;
}



  public User id(long id) {
    setId(id);
    return this;
  }

  public User username(String username) {
    setUsername(username);
    return this;
  }

  public User email(String email) {
    setEmail(email);
    return this;
  }

  public User address(String address) {
    setAddress(address);
    return this;
  }

  public User phone(String phone) {
    setPhone(phone);
    return this;
  }

  public User password(String password) {
    setPassword(password);
    return this;
  }

  public User confirmPassword(String confirmPassword) {
    setConfirmPassword(confirmPassword);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(address, user.address) && Objects.equals(phone, user.phone) && Objects.equals(password, user.password) && Objects.equals(confirmPassword, user.confirmPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, address, phone, password, confirmPassword);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", username='" + getUsername() + "'" +
      ", email='" + getEmail() + "'" +
      ", address='" + getAddress() + "'" +
      ", phone='" + getPhone() + "'" +
      ", password='" + getPassword() + "'" +
      ", confirmPassword='" + getConfirmPassword() + "'" +
      "}";
  }
    
}
