package com.arthur.springevents.card.usecases;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.card.domain.Card;
import com.arthur.springevents.card.repository.CardRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetCard {

  private final CardRepository repository;

  public Card execute(UUID cardId) {
    log.info("Getting card: {}", cardId);

    return repository.findById(cardId)
      .orElseThrow(EntityNotFoundException::new);
  }
}
