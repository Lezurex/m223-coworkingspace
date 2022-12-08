package com.lezurex.m223.coworkingspace.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({@NamedQuery(name = "ApplicationUser.findByEmail",
    query = "SELECT u FROM ApplicationUser u WHERE u.email = :email")})
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
  @NotBlank(message = "Firstname may not be blank.")
  private String firstname;
  @Column(nullable = false)
  @NotBlank(message = "Lastname may not be blank.")
  private String lastname;

  @Column(nullable = false)
  @Length(min = 8, message = "Password has to be at least 8 characters.")
  private String passwordHash;

  private String favouriteCoffee;

  @Enumerated(EnumType.STRING)
  private RoleEnum role;

  @OneToMany(mappedBy = "applicationUser")
  private Set<Booking> bookings;

  public ApplicationUser(@NotBlank(message = "Email may not be blank.") @Email String email,
      @NotBlank(message = "Firstname may not be blank.") String firstname,
      @NotBlank(message = "Lastname may not be blank.") String lastname,
      @Length(min = 8, message = "Password has to be at least 8 characters.") String passwordHash,
      RoleEnum role) {
    this.email = email;
    this.firstname = firstname;
    this.lastname = lastname;
    this.passwordHash = passwordHash;
    this.role = role;
  }

  public ApplicationUser() {}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setEmail(String username) {
    this.email = username;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  @JsonIgnore
  public Set<Booking> getBookings() {
    return bookings;
  }

  public String getFavouriteCoffee() {
    return favouriteCoffee;
  }

  public void setFavouriteCoffee(String favouriteCoffee) {
    this.favouriteCoffee = favouriteCoffee;
  }

}
