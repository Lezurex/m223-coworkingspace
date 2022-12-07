package com.lezurex.m223.coworkingspace.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ApplicationUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private long id;

  @Column(nullable = false, unique = true)
  @NotBlank(message = "Email may not be blank.")
  @Email
  private String email;

  @Column(nullable = false)
  @Length(min = 8)
  private String password;

  @OneToMany(mappedBy = "applicationUser")
  @JsonIgnore
  @Fetch(FetchMode.JOIN)
  private Set<Entry> entries;

  public ApplicationUser(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public ApplicationUser() {}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return email;
  }

  public void setEmail(String username) {
    this.email = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public Set<Entry> getEntries() {
    return entries;
  }

  public void setEntries(Set<Entry> entries) {
    this.entries = entries;
  }

}

