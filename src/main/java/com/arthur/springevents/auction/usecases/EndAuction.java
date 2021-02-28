package com.arthur.springevents.auction.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.arthur.springevents.auction.domain.Auction;
import com.arthur.springevents.auction.events.AuctionEnded;
import com.arthur.springevents.auction.repository.AuctionRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class EndAuction {

  private final AuctionRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public Auction execute(UUID auctionId) {
    log.info("Ending Auction: {}", auctionId);
    
    var auction = repository.findById(auctionId)
      .orElseThrow(EntityNotFoundException::new);
    
    auction.end();
    log.info("Auction ended");

    eventPublisher.publishEvent(new AuctionEnded(this, auction, UUID.randomUUID()));
    return auction;
  }
}
