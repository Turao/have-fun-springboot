package com.arthur.springevents.card.events;

import java.util.UUID;

import com.arthur.springevents.core.events.DomainModelEvent;
import com.arthur.springevents.card.domain.Card;

public class CardCreated extends DomainModelEvent<Card> {

  private static final long serialVersionUID = 1L;

  public CardCreated(Object source, Card model, UUID correlationId) {
    super(source, model, correlationId);
  }
}
