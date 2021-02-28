package com.arthur.springevents.match.usecases;

import java.util.UUID;

import com.arthur.springevents.match.domain.Match;
import com.arthur.springevents.match.events.MatchCreated;
import com.arthur.springevents.match.repository.MatchRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateMatch {
  
  private final MatchRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  public Match execute() {
    log.info("Creating Match");

    var auction = new Match();
    repository.save(auction);
    
    log.info("Match created: {}", auction);

    eventPublisher.publishEvent(new MatchCreated(this, auction, UUID.randomUUID()));
    return auction;
  }
}
