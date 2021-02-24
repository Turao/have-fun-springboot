package com.arthur.springevents.match;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.match.events.MatchCreated;
import com.arthur.springevents.match.events.MatchDeleted;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MatchService {
    @Autowired private MatchRepository repository;

    @Autowired private ApplicationEventPublisher eventPublisher;

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
}
