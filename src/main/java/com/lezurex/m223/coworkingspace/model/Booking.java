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

}
