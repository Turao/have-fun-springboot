package com.arthur.springevents.card.events;

import java.util.UUID;

import com.arthur.springevents.card.domain.Card;
import com.arthur.springevents.core.events.DomainModelEvent;

public class OwnerUnassigned extends DomainModelEvent<Card> {

  private static final long serialVersionUID = 1L;

  public OwnerUnassigned(Object source, Card model, UUID correlationId) {
    super(source, model, correlationId);
  }
  
}
