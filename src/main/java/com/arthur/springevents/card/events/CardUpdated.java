package com.arthur.springevents.card.events;

import java.util.UUID;

import com.arthur.springevents.core.events.DomainModelEvent;
import com.arthur.springevents.card.Card;

public class CardUpdated extends DomainModelEvent<Card> {

  private static final long serialVersionUID = 1L;

  public CardUpdated(Object source, Card model, UUID correlationId) {
    super(source, model, correlationId);
  }
}