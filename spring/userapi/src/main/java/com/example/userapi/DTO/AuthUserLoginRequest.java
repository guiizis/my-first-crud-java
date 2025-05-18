package com.example.userapi.DTO;

public class AuthUserLoginRequest {

    private String username;
    private String password;

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String newUserName) {
    this.username = newUserName;
  }
  
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String newPassword) {
    this.password = newPassword;
  }
}
