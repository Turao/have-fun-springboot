package com.arthur.springevents.match.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.arthur.springevents.match.domain.Match;
import com.arthur.springevents.match.events.MatchEnded;
import com.arthur.springevents.match.repository.MatchRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class EndMatch {

  private final MatchRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public Match execute(UUID matchId) {
    log.info("Ending Match: {}", matchId);
    
    var match = repository.findById(matchId)
      .orElseThrow(EntityNotFoundException::new);
    
    match.end();
    log.info("Match ended");

    eventPublisher.publishEvent(new MatchEnded(this, match, UUID.randomUUID()));
    return match;
  }
}
