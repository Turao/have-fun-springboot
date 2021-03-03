package com.arthur.springevents.auction.integration.dtos;

import java.util.UUID;

public class BidRequest {
  UUID userId;
  int price;

  public BidRequest(UUID userId, int price) {
      this.userId = userId;
      this.price = price;
  }

  public UUID getUserId() {
      return this.userId;
  }

  public int getPrice() {
      return this.price;
  }
}
