package com.arthur.springevents.domain.translation;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends CrudRepository<Translation, UUID> {}
