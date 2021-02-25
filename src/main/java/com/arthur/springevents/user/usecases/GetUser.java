package com.arthur.springevents.user.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.user.domain.User;
import com.arthur.springevents.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GetUser {

  @Autowired UserRepository repository;

  public User execute(UUID userId) {
    log.info("Getting user: {}", userId);

    return repository.findById(userId)
      .orElseThrow(EntityNotFoundException::new);
  }
}
