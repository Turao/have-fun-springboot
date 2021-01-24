package com.arthur.springevents.domain.translationrequest;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRequestRepository extends CrudRepository<TranslationRequest, UUID> {
    List<TranslationRequest> findAll();
}
