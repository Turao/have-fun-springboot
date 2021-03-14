package com.arthur.springevents.match.domain;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import lombok.ToString;

@ToString
@Entity
public class Match {
    @Id private UUID id;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    @ManyToMany(mappedBy = "matches")
    private Collection<Participant> participants;

    // only needed when starting the match
    // should we store them?
    @Transient
    private Collection<Rule> rules;

    public Match() {
        this.id = UUID.randomUUID();
        this.participants = new HashSet<>();
        this.rules = new HashSet<>();
    }

    public UUID getId() {
        return this.id;
    }

    protected void setId(UUID id) {
        this.id = id;
    }

    public Collection<Participant> getParticipants() {
        return this.participants;
    }

    protected void setParticipants(Collection<Participant> participants) {
        this.participants = participants;
    }

    protected void setRules(Collection<Rule> rules) {
        this.rules = rules;
    }

    public Collection<Rule> getRules() {
        return this.rules;
    }

    public Match addRule(Rule rule) {
        this.rules.add(rule);
        return this;
    }

    public void start() {
        if (startDate != null) throw new IllegalStateException("Match has already started");
        if (participants.isEmpty()) throw new IllegalStateException("Match has no participants");
        
        // match MUST satisfy all of its rules before starting
        rules.forEach(rule -> rule.check(this));

        this.startDate = OffsetDateTime.now();
      }
    
      public void end() {
        if (startDate == null) throw new IllegalStateException("Match has not started yet");
        if (endDate != null) throw new IllegalStateException("Match has already ended");
        this.endDate = OffsetDateTime.now();
      }
}
