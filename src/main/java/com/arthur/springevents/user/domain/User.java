package com.arthur.springevents.user.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.ToString;

@Entity
@ToString
public class User {
    @Id private UUID id;

    public User() {}

    public User(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
