package com.arthur.springevents.domain.request;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, UUID> {
  List<Request> findAll();
}
