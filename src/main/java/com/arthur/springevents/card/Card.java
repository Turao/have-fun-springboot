package com.arthur.springevents.card;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Card {
    @Id private UUID id;

    public Card() {}

    public Card(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
