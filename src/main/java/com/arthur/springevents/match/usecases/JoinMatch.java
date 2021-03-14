package com.arthur.springevents.match.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.arthur.springevents.match.domain.Participant;
import com.arthur.springevents.match.repository.MatchRepository;
import com.arthur.springevents.match.repository.ParticipantRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinMatch {
  private final ParticipantRepository participantRepository;
  private final MatchRepository matchRepository;

  @Transactional
  public Participant execute(UUID participantId, UUID matchId) {
    log.info("Adding Participant {} to Match {}", participantId, matchId);
    var match = matchRepository.findById(matchId)
      .orElseThrow(EntityNotFoundException::new);

    var participant = participantRepository.findById(participantId)
      .orElseGet(() -> new Participant(participantId));
    
    participant.join(match);
    
    log.info("Participant added to match");
    return participant;
  }
}
