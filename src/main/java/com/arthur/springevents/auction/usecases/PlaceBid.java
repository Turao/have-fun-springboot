package com.arthur.springevents.auction.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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

  @Transactional
  public Auction execute(UUID auctionId, UUID userId, int price) {
    log.info("Placing bid from User: {} with Price: {} at Auction: {}",
      userId, price, auctionId);

    var auction = repository.findById(auctionId)
      .orElseThrow(EntityNotFoundException::new);
    
    var bid = auction.placeBid(userId, price);
    log.info("Bid placed");

    eventPublisher.publishEvent(new BidPlaced(this, bid, UUID.randomUUID()));
    return auction;
  }
}
