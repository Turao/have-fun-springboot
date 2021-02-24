package com.arthur.springevents.user.events;

import java.util.UUID;

import com.arthur.springevents.core.events.DomainModelEvent;
import com.arthur.springevents.user.User;

public class UserUpdated extends DomainModelEvent<User> {

  private static final long serialVersionUID = 1L;

  public UserUpdated(Object source, User model, UUID correlationId) {
    super(source, model, correlationId);
  }
}