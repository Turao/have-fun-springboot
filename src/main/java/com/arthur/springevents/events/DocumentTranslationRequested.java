package com.arthur.springevents.events;

import com.arthur.springevents.domain.document.Document;
import java.util.UUID;
import lombok.ToString;

@ToString
public class DocumentTranslationRequested extends EnhancedEvent {
    private static final long serialVersionUID = 1L;

    private Document document;
    private String targetLanguage;

    public DocumentTranslationRequested(
            Object source, UUID correlationId, Document document, String targetLanguage) {
        super(source, correlationId);
        this.document = document;
        this.targetLanguage = targetLanguage;
    }

    public Document getDocument() {
        return this.document;
    }

    public String getTargetLanguage() {
        return this.targetLanguage;
    }
}
