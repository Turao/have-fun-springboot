package com.arthur.springevents.card.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.arthur.springevents.card.domain.Card;
import com.arthur.springevents.card.events.OwnerAssigned;
import com.arthur.springevents.card.repository.CardRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssignOwner {
  
  private final CardRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public Card execute(UUID cardId,  UUID ownerId) {
    log.info("Assigning User: {} as owner of Card: {}", ownerId, cardId);
    
    var card = repository.findById(cardId)
      .orElseThrow(EntityNotFoundException::new);
    card.assignOwner(ownerId);

    log.info("Owner assigned to Card: {}", card);
    
    eventPublisher.publishEvent(new OwnerAssigned(this, card, UUID.randomUUID()));
    return card;
  }
}
