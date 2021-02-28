package com.arthur.springevents.match;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.match.events.MatchCreated;
import com.arthur.springevents.match.events.MatchDeleted;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public Match create() {
        log.info("Creating Match...");

        var match = new Match(UUID.randomUUID());
        match = repository.save(match);
        log.info("Match created: {}", match);

        eventPublisher.publishEvent(new MatchCreated(this, match, UUID.randomUUID()));
        return match;
    }

    public Match delete(UUID matchId) {
        var match = repository.findById(matchId)
            .orElseThrow(EntityNotFoundException::new);

        log.info("Deleting match: {}", matchId);
        repository.deleteById(matchId);
        log.info("Match deleted: {}", matchId);
        
        eventPublisher.publishEvent(new MatchDeleted(this, match, UUID.randomUUID()));
        return match;
    }

    public Match findById(UUID matchId) {
        return repository.findById(matchId)
            .orElseThrow(EntityNotFoundException::new);
    }
}
