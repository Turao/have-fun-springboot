package com.arthur.springevents.user.usecases;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import com.arthur.springevents.user.domain.User;
import com.arthur.springevents.user.events.UserUpdated;
import com.arthur.springevents.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateUser {
  @Autowired private UserRepository repository;
  @Autowired private ApplicationEventPublisher eventPublisher;

  public User execute(User user) {
    log.info("Updating user: {}", user);
    var updated = repository.save(user);
    log.info("User updated: {}", user);
    
    eventPublisher.publishEvent(new UserUpdated(this, updated, UUID.randomUUID()));
    return updated;
}
}
