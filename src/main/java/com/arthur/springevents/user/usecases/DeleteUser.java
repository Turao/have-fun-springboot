package com.arthur.springevents.user.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.user.domain.User;
import com.arthur.springevents.user.events.UserDeleted;
import com.arthur.springevents.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteUser {

  private final UserRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  public User execute(UUID userId) {
    var user = repository.findById(userId)
        .orElseThrow(EntityNotFoundException::new);

    log.info("Deleting user: {}", userId);
    repository.deleteById(userId);
    log.info("User deleted: {}", userId);
    
    eventPublisher.publishEvent(new UserDeleted(this, user, UUID.randomUUID()));
    return user;
}
}
