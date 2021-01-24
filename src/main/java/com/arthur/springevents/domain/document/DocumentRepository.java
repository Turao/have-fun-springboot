package com.arthur.springevents.domain.document;

import java.util.Set;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, UUID> {
    Set<Document> findAllByTextContainingAndLanguage(String text, String language);
}
