package com.lezurex.m223.coworkingspace.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class Credential {

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Length(min = 8)
  private String password;

  public Credential(@NotBlank @Email String email, @NotBlank @Length(min = 8) String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
