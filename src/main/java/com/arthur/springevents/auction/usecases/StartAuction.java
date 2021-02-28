package com.arthur.springevents.auction.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.auction.domain.Auction;
import com.arthur.springevents.auction.events.AuctionStarted;
import com.arthur.springevents.auction.repository.AuctionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StartAuction {

  @Autowired private AuctionRepository repository;

  @Autowired private ApplicationEventPublisher eventPublisher;

  public Auction execute(UUID auctionId) {
    log.info("Starting a new Auction");
    
    var auction = repository.findById(auctionId)
      .orElseThrow(EntityNotFoundException::new);

    auction.start();
    repository.save(auction);
    
    log.info("New Auction started: {}", auction);

    eventPublisher.publishEvent(new AuctionStarted(this, auction, UUID.randomUUID()));
    return auction;
  }
}
