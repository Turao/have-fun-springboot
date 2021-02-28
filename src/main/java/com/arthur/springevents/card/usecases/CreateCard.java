package com.arthur.springevents.card.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.UUID;

import com.arthur.springevents.card.domain.Card;
import com.arthur.springevents.card.events.CardCreated;
import com.arthur.springevents.card.repository.CardRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCard {
  
  private final CardRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  public Card execute() {
    log.info("Creating Card...");

    var card = new Card(new Random().nextInt(100));
    card = repository.save(card);
    log.info("Card created: {}", card);
    
    eventPublisher.publishEvent(new CardCreated(this, card, UUID.randomUUID()));
    return card;
  }

}
