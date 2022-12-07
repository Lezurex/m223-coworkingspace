package com.lezurex.m223.coworkingspace.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private long id;

  @Column(nullable = false)
  @NotBlank(message = "Title may not be blank.")
  private String title;

  @ManyToMany(mappedBy = "tags")
  @JsonIgnore
  @Fetch(FetchMode.JOIN)
  private Set<Entry> entries;

  public Tag(String title) {
    this.title = title;
  }

  public Tag() {}


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<Entry> getEntries() {
    return entries;
  }

  public void setEntries(Set<Entry> entries) {
    this.entries = entries;
  }

}
