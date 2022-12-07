package com.lezurex.m223.coworkingspace.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private long id;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TimeframeEnum timeframe;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private StatusEnum status;

  @ManyToOne
  private ApplicationUser applicationUser;

  public Booking(LocalDate date, TimeframeEnum timeframe, StatusEnum status,
      ApplicationUser applicationUser) {
    this.date = date;
    this.timeframe = timeframe;
    this.status = status;
    this.applicationUser = applicationUser;
  }

  public Booking() {}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public TimeframeEnum getTimeframe() {
    return timeframe;
  }

  public void setTimeframe(TimeframeEnum timeframe) {
    this.timeframe = timeframe;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public ApplicationUser getApplicationUser() {
    return applicationUser;
  }

  public void setApplicationUser(ApplicationUser applicationUser) {
    this.applicationUser = applicationUser;
  }

}
