package com.arthur.springevents.auction.domain;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bid {
  @Id public UUID id;

  @ManyToOne
  private Auction auction;

  // the user that bids on some item
  private UUID userId;
  
  // the item that was bidded on
  private UUID itemId;
  
  private int price;

  private OffsetDateTime placedAt;

  protected Bid() {}

  public Bid(UUID userId, UUID itemId, int price) {
    this.id = UUID.randomUUID();

    this.userId = userId;
    this.itemId = itemId;

    // todo: replace for Money API
    if (price < 0) throw new IllegalArgumentException("Price should not be negative");
    this.price = price;

    this.placedAt = OffsetDateTime.now();
  }

  public UUID getId() {
    return this.id;
  }

  public UUID getUserId() {
    return this.userId;
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

}
