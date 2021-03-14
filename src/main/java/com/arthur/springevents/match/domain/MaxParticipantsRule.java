package com.arthur.springevents.match.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MaxParticipantsRule implements Rule {

  private final Integer maxParticipants;

  @Override
  public void check(Match match) throws IllegalStateException {
    if(match.getParticipants().size() > maxParticipants) {
      throw new IllegalStateException("Number of participants does not comply to rule");
    }
  }
  
}
