package com.arthur.springevents.match;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.ToString;

@ToString
@Entity
public class Match {
    @Id private UUID id;

    public Match() {}

    public Match(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
