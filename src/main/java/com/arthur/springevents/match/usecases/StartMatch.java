package com.arthur.springevents.match.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.match.domain.Match;
import com.arthur.springevents.match.events.MatchStarted;
import com.arthur.springevents.match.repository.MatchRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StartMatch {

  private final MatchRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  public Match execute(UUID matchId) {
    log.info("Starting Match: {}", matchId);
    
    var match = repository.findById(matchId)
      .orElseThrow(EntityNotFoundException::new);

    match.start();
    repository.save(match);
    
    log.info("Match started: {}", match);

    eventPublisher.publishEvent(new MatchStarted(this, match, UUID.randomUUID()));
    return match;
  }
}
