package com.arthur.springevents.card.usecases;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.UUID;

import com.arthur.springevents.card.domain.Card;
import com.arthur.springevents.card.events.CardCreated;
import com.arthur.springevents.card.repository.CardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateCard {
  @Autowired private CardRepository repository;
  @Autowired private ApplicationEventPublisher eventPublisher;

  public Card execute() {
    log.info("Creating Card...");

    var card = new Card(UUID.randomUUID(), new Random().nextInt(100));
    card = repository.save(card);
    log.info("Card created: {}", card);
    
    eventPublisher.publishEvent(new CardCreated(this, card, UUID.randomUUID()));
    return card;
  }

}
