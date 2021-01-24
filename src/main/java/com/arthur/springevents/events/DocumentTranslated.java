package com.arthur.springevents.events;

import com.arthur.springevents.domain.document.Document;
import java.util.UUID;
import lombok.ToString;

@ToString
public class DocumentTranslated extends EnhancedEvent {

    private static final long serialVersionUID = 1L;

    private Document document;

    public DocumentTranslated(Object source, UUID correlationId, Document document) {
        super(source, correlationId);
        this.document = document;
    }

    public Document getDocument() {
        return this.document;
    }

    public String getTranslatedLanguage() {
        return this.document.getLanguage();
    }
}
