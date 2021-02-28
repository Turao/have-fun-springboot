package com.arthur.springevents.card.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.arthur.springevents.card.domain.Card;
import com.arthur.springevents.card.events.CardExpired;
import com.arthur.springevents.card.repository.CardRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpireCard {
  
  private final CardRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public Card execute(UUID cardId) {
    log.info("Expiring Card: {}", cardId);
    
    var card = repository.findById(cardId)
      .orElseThrow(EntityNotFoundException::new);
    
    card.expire();
    log.info("Card expired: {}", cardId);
    
    eventPublisher.publishEvent(new CardExpired(this, card, UUID.randomUUID()));
    return card;
  }
}
