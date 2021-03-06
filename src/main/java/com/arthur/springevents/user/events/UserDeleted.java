package com.arthur.springevents.user.events;

import java.util.UUID;

import com.arthur.springevents.core.events.DomainModelEvent;
import com.arthur.springevents.user.domain.User;

public class UserDeleted extends DomainModelEvent<User> {

  private static final long serialVersionUID = 1L;

  public UserDeleted(Object source, User model, UUID correlationId) {
    super(source, model, correlationId);
  }
}