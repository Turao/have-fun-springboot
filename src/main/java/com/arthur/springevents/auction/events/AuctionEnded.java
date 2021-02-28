package com.arthur.springevents.auction.events;

import java.util.UUID;

import com.arthur.springevents.auction.domain.Auction;
import com.arthur.springevents.core.events.DomainModelEvent;

public class AuctionEnded extends DomainModelEvent<Auction> {

  private static final long serialVersionUID = 1L;

  public AuctionEnded(Object source, Auction model, UUID correlationId) {
    super(source, model, correlationId);
  }

}
