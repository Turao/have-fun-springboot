package com.arthur.springevents.auction.usecases;

import java.util.UUID;

import com.arthur.springevents.auction.domain.Auction;
import com.arthur.springevents.auction.events.AuctionCreated;
import com.arthur.springevents.auction.repository.AuctionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreateAuction {
  
  @Autowired private AuctionRepository repository;

  @Autowired private ApplicationEventPublisher eventPublisher;

  public Auction execute() {
    log.info("Creating Auction");

    var auction = new Auction();
    repository.save(auction);
    
    log.info("Auction created: {}", auction);

    eventPublisher.publishEvent(new AuctionCreated(this, auction, UUID.randomUUID()));
    return auction;
  }
}
