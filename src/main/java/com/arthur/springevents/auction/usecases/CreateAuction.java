package com.arthur.springevents.auction.usecases;

import java.util.UUID;

import com.arthur.springevents.auction.domain.Auction;
import com.arthur.springevents.auction.events.AuctionCreated;
import com.arthur.springevents.auction.repository.AuctionRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateAuction {
  
  private final AuctionRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  public Auction execute(UUID itemId) {
    log.info("Creating Auction");

    var auction = new Auction(itemId);
    repository.save(auction);
    
    log.info("Auction created: {}", auction);

    eventPublisher.publishEvent(new AuctionCreated(this, auction, UUID.randomUUID()));
    return auction;
  }
}
