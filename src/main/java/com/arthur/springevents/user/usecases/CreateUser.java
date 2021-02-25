package com.arthur.springevents.user.usecases;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import com.arthur.springevents.user.domain.User;
import com.arthur.springevents.user.events.UserCreated;
import com.arthur.springevents.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateUser {
  @Autowired private UserRepository repository;
  @Autowired private ApplicationEventPublisher eventPublisher;

  public User execute() {
    log.info("Creating Document...");

    var user = new User(UUID.randomUUID());
    user = repository.save(user);
    log.info("User created: {}", user);
    
    eventPublisher.publishEvent(new UserCreated(this, user, UUID.randomUUID()));
    return user;
  }

}
