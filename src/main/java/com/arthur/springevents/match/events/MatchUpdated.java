package com.arthur.springevents.match.events;

import java.util.UUID;

import com.arthur.springevents.core.events.DomainModelEvent;
import com.arthur.springevents.match.Match;

public class MatchUpdated extends DomainModelEvent<Match> {

  private static final long serialVersionUID = 1L;

  public MatchUpdated(Object source, Match model, UUID correlationId) {
    super(source, model, correlationId);
  }
}