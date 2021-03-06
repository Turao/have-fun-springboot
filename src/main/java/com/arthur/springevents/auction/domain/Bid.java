package com.arthur.springevents.auction.domain;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bid implements Comparable<Bid> {
  @Id public UUID id;

  @ManyToOne
  private Auction auction;

  // the user that bids on some item
  private UUID bidderId;
  
  private int price;

  private OffsetDateTime placedAt;

  @Enumerated(EnumType.STRING)
  private BidStatus status;

  protected Bid() {}

  public Bid(Auction auction, UUID bidderId, int price) {
    this.id = UUID.randomUUID();
    
    this.auction = auction;
    this.bidderId = bidderId;

    this.status = BidStatus.OPEN;

    // todo: replace for Money API
    if (price < 0) throw new IllegalArgumentException("Price should not be negative");
    this.price = price;

    this.placedAt = OffsetDateTime.now();
  }

  public String toString() {
    return "Bid=(" +
        "id=" + this.id +
        ", auction=" + this.auction.getId() + // todo: validate to avoid null pointers
        ", bidderId=" + this.bidderId +
        ", price=" + this.price +
        ", status=" + this.status +
        ")";
}

  public UUID getId() {
    return this.id;
  }

  public UUID getBidderId() {
    return this.bidderId;
  }

  public int getPrice() {
    return this.price;
  }

  public OffsetDateTime getPlacedAt() {
    return this.placedAt;
  }

  @Override
  public int compareTo(Bid other) {
    var highestPriceFirst = Comparator.comparing(Bid::getPrice);
    var earliestBidFirst = Comparator.comparing(Bid::getPlacedAt).reversed();

    return highestPriceFirst
      .thenComparing(earliestBidFirst)
      .compare(this, other);
  }

  
  public void closeAsWinner() {
    if (!BidStatus.OPEN.equals(this.status)) throw new IllegalStateException("This bid has already been closed");
   this.status = BidStatus.WINNER;
  }

  public void closeAsLoser() {
    if (!BidStatus.OPEN.equals(this.status)) throw new IllegalStateException("This bid has already been closed");
   this.status = BidStatus.LOSER;
  }
  
}
