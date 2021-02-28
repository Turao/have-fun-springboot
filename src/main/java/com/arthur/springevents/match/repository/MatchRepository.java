package com.arthur.springevents.match.repository;

import java.util.UUID;

import com.arthur.springevents.match.domain.Match;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<Match, UUID> {}
