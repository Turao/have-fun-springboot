package com.arthur.springevents.auction.domain;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bid implements Comparable<Bid> {
  @Id public UUID id;

  @ManyToOne
  private Auction auction;

  // the user that bids on some item
  private UUID bidderId;
  
  // the item that was bidded on
  private UUID itemId;
  
  private int price;

  private OffsetDateTime placedAt;

  protected Bid() {}

  public Bid(Auction auction, UUID bidderId, UUID itemId, int price) {
    this.id = UUID.randomUUID();
    
    this.auction = auction;
    this.bidderId = bidderId;
    this.itemId = itemId;

    // todo: replace for Money API
    if (price < 0) throw new IllegalArgumentException("Price should not be negative");
    this.price = price;

    this.placedAt = OffsetDateTime.now();
  }

  public UUID getId() {
    return this.id;
  }

  public UUID getBidderId() {
    return this.bidderId;
  }

  public UUID getItemId() {
    return this.itemId;
  }

  public int getPrice() {
    return this.price;
  }

  public OffsetDateTime getPlacedAt() {
    return this.placedAt;
  }

  @Override
  public int compareTo(Bid other) {
    return Integer.compare(this.price, other.getPrice());
  }

}
