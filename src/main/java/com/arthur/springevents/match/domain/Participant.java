package com.arthur.springevents.match.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Participant {

  @Id private UUID id;
  
  @ElementCollection(fetch = FetchType.EAGER)
  private Collection<UUID> deck;
  
  @ManyToMany
  private Collection<Match> matches;

  public Participant(UUID id) {    
    this.id = id;

    this.deck = new HashSet<>();
    this.matches = new HashSet<>();
  }

  protected UUID getId() {
    return this.id;
  }

  protected void setId(UUID id) {
    this.id = id;
  }

  public Collection<UUID> getDeck() {
    return this.deck;
  }

  public Participant addCard(UUID cardId) {
    deck.add(cardId);
    return this;
  }

  public Collection<Match> getMatches() {
    return this.matches;
  }

  protected void setMatches(Collection<Match> matches) {
    this.matches = matches;
  }

  public Participant join(Match match) {
    if (matches.contains(match)) throw new IllegalArgumentException("Participant has already joined this Match");

    matches.add(match);
    return this;
  }
}
