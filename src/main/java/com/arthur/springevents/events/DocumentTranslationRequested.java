package com.arthur.springevents.events;

import com.arthur.springevents.domain.document.Document;
import java.util.Collection;
import java.util.UUID;
import lombok.ToString;

@ToString
public class DocumentTranslationRequested extends EnhancedEvent {
    private static final long serialVersionUID = 1L;

    private Document document;
    private Collection<String> targetLanguages;

    public DocumentTranslationRequested(
            Object source,
            UUID correlationId,
            Document document,
            Collection<String> targetLanguages) {
        super(source, correlationId);
        this.document = document;
        this.targetLanguages = targetLanguages;
    }

    public Document getDocument() {
        return this.document;
    }

    public Collection<String> getTargetLanguages() {
        return this.targetLanguages;
    }
}
