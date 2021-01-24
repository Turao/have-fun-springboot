package com.arthur.springevents.domain.translationrequest;

import com.arthur.springevents.domain.document.Document;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.ToString;

@ToString
@Entity
public class TranslationRequest {
    @Id private UUID id;

    @ManyToOne private Document document;

    @ElementCollection private Collection<String> targetLanguages;

    private final OffsetDateTime createdAt = OffsetDateTime.now();

    public TranslationRequest() {}

    public TranslationRequest(
            UUID id,
            Document document,
            String originalLanguage,
            Collection<String> targetLanguages) {
        this.id = id;
        this.document = document;
        this.targetLanguages = targetLanguages;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Collection<String> getTargetLanguages() {
        return this.targetLanguages;
    }

    public void setTargetLanguages(Collection<String> targetLanguages) {
        this.targetLanguages = targetLanguages;
    }

    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }
}
