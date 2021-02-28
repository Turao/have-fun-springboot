package com.arthur.springevents.auction.events;

import java.util.UUID;

import com.arthur.springevents.auction.domain.Bid;
import com.arthur.springevents.core.events.DomainModelEvent;

public class BidPlaced extends DomainModelEvent<Bid> {

  private static final long serialVersionUID = 1L;

  public BidPlaced(Object source, Bid model, UUID correlationId) {
    super(source, model, correlationId);
  }
  
}
