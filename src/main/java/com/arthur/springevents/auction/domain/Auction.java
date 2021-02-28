package com.arthur.springevents.auction.domain;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Auction {
  @Id private UUID id;

  private OffsetDateTime startDate;

  private OffsetDateTime endDate;

  public Auction() {
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return this.id;
  }

  public UUID setId(UUID id) {
    return this.id;
  }

  public void start() {
    if (startDate != null) throw new IllegalStateException("Auction has already started");
    this.startDate = OffsetDateTime.now();
  }

  public void end() {
    if (startDate == null) throw new IllegalStateException("Auction has not started yet");
    if (endDate != null) throw new IllegalStateException("Auction has already ended");
    this.endDate = OffsetDateTime.now();
  }
}
