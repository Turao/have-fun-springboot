package com.arthur.springevents.card.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Card {
    @Id private UUID id;

    private Integer points;

    private CardStatus status;

    public Card() {}

    public Card(UUID id, Integer points) {
        this.id = id;
        this.points = points;
        this.status = CardStatus.ACTIVE;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Double getPrice() {
        return this.points * 1.5;
    }

    public void expire() {
        this.status = CardStatus.EXPIRED;
    }
}
