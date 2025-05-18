package com.example.userapi.dto;

public class AuthUserLoginRequest {

    private String username;
    private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String newUserName) {
    username = newUserName;
  }
  
  public String getPassword() {
    return password;
  }

  public void setPassword(String newPassword) {
    password = newPassword;
  }
}
