package com.arthur.springevents.auction.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.auction.domain.Auction;
import com.arthur.springevents.auction.events.AuctionStarted;
import com.arthur.springevents.auction.repository.AuctionRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StartAuction {

  private final AuctionRepository repository;
  private final ApplicationEventPublisher eventPublisher;

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
