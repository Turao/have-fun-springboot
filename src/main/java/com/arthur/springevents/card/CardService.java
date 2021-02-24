package com.arthur.springevents.card;

import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.card.events.CardCreated;
import com.arthur.springevents.card.events.CardDeleted;
import com.arthur.springevents.card.events.CardUpdated;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardService {

    @Autowired private ApplicationEventPublisher eventPublisher;

    @Autowired private CardRepository repository;

    public CardService() {}

    public Card create() {
        log.info("Creating Card...");

        var card = new Card(UUID.randomUUID(), new Random().nextInt(100)) ;
        card = repository.save(card);
        log.info("Card created!");

        eventPublisher.publishEvent(new CardCreated(this, card, UUID.randomUUID()));
        return card;
    }

    public Card update(Card card) {
        log.info("Updating Card: {}", card);
        var updated = repository.save(card);
        log.info("Card updated!");

        eventPublisher.publishEvent(new CardUpdated(this, updated, UUID.randomUUID()));
        return updated;
    }

    public Card delete(UUID cardId) {
        var card = repository.findById(cardId)
            .orElseThrow(EntityNotFoundException::new);

        log.info("Deleting card: {}", cardId);
        repository.deleteById(cardId);
        log.info("Card deleted: {}", cardId);
        
        eventPublisher.publishEvent(new CardDeleted(this, card, UUID.randomUUID()));
        return card;
    }

    public Card findById(UUID cardId) {
        return repository.findById(cardId)
            .orElseThrow(EntityNotFoundException::new);
    }
}
