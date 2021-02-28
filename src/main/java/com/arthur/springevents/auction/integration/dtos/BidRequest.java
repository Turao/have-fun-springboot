package com.arthur.springevents.auction.integration.dtos;

import java.util.UUID;

public class BidRequest {
  UUID userId;
  UUID itemId;
  int price;

  public BidRequest(UUID userId, UUID itemId, int price) {
      this.userId = userId;
      this.itemId = itemId;
      this.price = price;
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
}
