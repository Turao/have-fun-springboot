package com.arthur.springevents.card.repository;

import java.util.UUID;

import com.arthur.springevents.card.domain.Card;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, UUID> {}
