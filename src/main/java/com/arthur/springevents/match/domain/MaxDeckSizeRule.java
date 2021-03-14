package com.arthur.springevents.match.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MaxDeckSizeRule implements Rule {

  private final Integer maxDeckSize;

  @Override
  public void check(Match match) throws IllegalStateException {
    boolean isSatisfied = match.getParticipants()
      .stream()
      .map(Participant::getDeck)
      .noneMatch(deck -> deck.size() > maxDeckSize);
    
    if (!isSatisfied) {
      throw new IllegalStateException("Participant deck should not exceed maximum size of: " + maxDeckSize);
    }
  }
  
}
