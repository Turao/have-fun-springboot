package com.arthur.springevents.auction.domain;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Auction {
  @Id private UUID id;

  private OffsetDateTime startDate;

  private OffsetDateTime endDate;

  @OneToMany(
    mappedBy = "auction",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
  private Collection<Bid> bids;

  public Auction() {
    this.id = UUID.randomUUID();
    this.bids = new ArrayList<>();
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

  public Bid placeBid(UUID userId, UUID itemId, int price) {
    if (startDate == null) throw new IllegalStateException("Cannot place bid. Auction has not started yet");
    if (endDate != null) throw new IllegalStateException("Cannot place bid. Auction has already ended");

    var bid = new Bid(userId, itemId, price);
    this.bids.add(bid);
    return bid;
  }

  public Collection<Bid> getBids() {
    return this.bids;
  }
}
