package com.arthur.springevents.user.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.user.domain.User;
import com.arthur.springevents.user.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetUser {

  private final UserRepository repository;

  public User execute(UUID userId) {
    log.info("Getting user: {}", userId);

    return repository.findById(userId)
      .orElseThrow(EntityNotFoundException::new);
  }
}
