package com.arthur.springevents.auction.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.auction.events.AuctionEnded;
import com.arthur.springevents.auction.repository.AuctionRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrantItemToHighestBidder {
  
  private final AuctionRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  @EventListener(AuctionEnded.class)
  void onAuctionEnded(AuctionEnded event) {
    var auction = event.getModel();
    execute(auction.getId()); // todo: do not pass entire model to event, but rather its ID
  }

  public void execute(UUID auctionId) {
    log.info("Granting Item to highest bidder of Auction: {}", auctionId);
    
    var auction = repository.findById(auctionId)
      .orElseThrow(EntityNotFoundException::new);

    var highestBid = auction.getHighestBid();
    log.info("Highest Bid: {}", highestBid);
    
    // todo: communicate with Card modules to change ownership of item

    log.info("Item granted!");
  }

}
