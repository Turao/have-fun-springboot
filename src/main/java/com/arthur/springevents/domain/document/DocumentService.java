package com.arthur.springevents.domain.document;

import java.util.Set;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DocumentService {

    @Autowired private DocumentRepository repository;

    public Document create(String text, String language) {
        log.info("Creating Document...");

        var document = new Document(UUID.randomUUID(), text, language);
        document = repository.save(document);

        log.info("Document created: {}", document);
        return document;
    }

    public Document update(Document document) {
        log.info("Updating Document: {}", document);
        return repository.save(document);
    }

    public Set<Document> findAllContainingAndLanguage(String text, String language) {
        return repository.findAllByTextContainingAndLanguage(text, language);
    }
}
