package com.arthur.springevents.user.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import com.arthur.springevents.user.domain.User;
import com.arthur.springevents.user.events.UserCreated;
import com.arthur.springevents.user.repository.UserRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUser {

  private final UserRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  public User execute() {
    log.info("Creating Document...");

    var user = new User();
    user = repository.save(user);
    log.info("User created: {}", user);
    
    eventPublisher.publishEvent(new UserCreated(this, user, UUID.randomUUID()));
    return user;
  }

}
