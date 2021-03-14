package com.arthur.springevents.match.repository;

import java.util.UUID;

import com.arthur.springevents.match.domain.Participant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, UUID> {}
