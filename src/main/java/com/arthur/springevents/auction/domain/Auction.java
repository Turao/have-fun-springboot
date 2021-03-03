package com.arthur.springevents.auction.domain;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Auction {
  @Id private UUID id;

  private UUID itemId;

  private OffsetDateTime startDate;

  private OffsetDateTime endDate;

  @OneToMany(
    mappedBy = "auction",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
  private Collection<Bid> bids;

  protected Auction() {}

  public Auction(UUID itemId) {
    this.id = UUID.randomUUID();
    this.itemId = itemId;
    this.bids = new ArrayList<>();
  }

  public String toString() {
    return "Auction=(" +
        "id=" + this.id +
        ", itemId=" + this.itemId +
        ", startDate=" + this.startDate +
        ", endDate=" + this.endDate +
        ", bids=" + this.bids +
        ")";
}

  public UUID getId() {
    return this.id;
  }

  protected UUID setId(UUID id) {
    return this.id;
  }

  public UUID getItemId() {
    return this.itemId;
  }

  protected void setItemId(UUID itemId) {
    this.itemId = itemId;
  }

  public void start() {
    if (startDate != null) throw new IllegalStateException("Auction has already started");
    
    this.startDate = OffsetDateTime.now();
  }

  public void end() {
    if (startDate == null) throw new IllegalStateException("Auction has not started yet");
    if (endDate != null) throw new IllegalStateException("Auction has already ended");
    
    this.endDate = OffsetDateTime.now();

    var highestBid = getHighestBid();
    this.bids
      .stream()
      .filter(bid -> !bid.equals(highestBid))
      .forEach(Bid::closeAsLoser);
    highestBid.closeAsWinner();
  }

  public Bid placeBid(UUID userId, int price) {
    if (startDate == null) throw new IllegalStateException("Cannot place bid. Auction has not started yet");
    if (endDate != null) throw new IllegalStateException("Cannot place bid. Auction has already ended");

    var bid = new Bid(this, userId, price);
    this.bids.add(bid);
    return bid;
  }

  public Collection<Bid> getBids() {
    return this.bids;
  }

  @JsonIgnore // todo: remove later
  public Bid getHighestBid() {
    return Collections.max(this.bids);
  }
}
