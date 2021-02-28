package com.arthur.springevents.auction.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.auction.domain.Auction;
import com.arthur.springevents.auction.events.BidPlaced;
import com.arthur.springevents.auction.repository.AuctionRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceBid {
  
  private final AuctionRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  public Auction execute(UUID auctionId, UUID userId, UUID itemId, int price) {
    log.info("Placing bid from User: {} for Item: {} with Price: {} at Auction: {}",
      userId, itemId, price, auctionId);

    var auction = repository.findById(auctionId)
      .orElseThrow(EntityNotFoundException::new);
    
    var bid = auction.placeBid(userId, itemId, price);
    log.info("Bid placed");

    eventPublisher.publishEvent(new BidPlaced(this, bid, UUID.randomUUID()));
    return auction;
  }
}
