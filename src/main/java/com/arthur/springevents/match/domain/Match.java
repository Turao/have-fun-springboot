package com.arthur.springevents.match.domain;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.ToString;

@ToString
@Entity
public class Match {
    @Id private UUID id;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    public Match() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void start() {
        if (startDate != null) throw new IllegalStateException("Match has already started");
        this.startDate = OffsetDateTime.now();
      }
    
      public void end() {
        if (startDate == null) throw new IllegalStateException("Match has not started yet");
        if (endDate != null) throw new IllegalStateException("Match has already ended");
        this.endDate = OffsetDateTime.now();
      }
}
