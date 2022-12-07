package com.lezurex.m223.coworkingspace.model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@NamedQueries({@NamedQuery(name = "ApplicationUser.findByEmail",
    query = "SELECT u FROM ApplicationUser u WHERE u.email = :email")})
public class Entry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime checkIn;

  @Column(nullable = false)
  private LocalDateTime checkOut;

  @ManyToOne()
  @Fetch(FetchMode.JOIN)
  @JsonIgnore
  private Category category;

  @ManyToOne(optional = false)
  @Fetch(FetchMode.JOIN)
  @JsonIgnore
  private ApplicationUser applicationUser;

  @ManyToMany
  @JoinTable(name = "entry_tags", joinColumns = @JoinColumn(name = "entry_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  @JsonIgnoreProperties("entries")
  @Fetch(FetchMode.JOIN)
  private Set<Tag> tags;

  public Entry(LocalDateTime checkIn, LocalDateTime checkOut) {
    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }

  public Entry() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(LocalDateTime checkIn) {
    this.checkIn = checkIn;
  }

  public LocalDateTime getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(LocalDateTime checkOut) {
    this.checkOut = checkOut;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Set<Tag> getTags() {
    return tags;
  }

  public void setTags(Set<Tag> tags) {
    this.tags = tags;
  }

  public ApplicationUser getApplicationUser() {
    return applicationUser;
  }

  public void setApplicationUser(ApplicationUser applicationUser) {
    this.applicationUser = applicationUser;
  }

}
