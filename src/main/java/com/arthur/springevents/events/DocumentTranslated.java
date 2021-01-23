package com.arthur.springevents.events;

import java.util.UUID;

import com.arthur.springevents.domain.document.Document;

import lombok.ToString;

@ToString
public class DocumentTranslated extends EnhancedEvent {
  
  private static final long serialVersionUID = 1L;
  
  private Document document;
  private String translatedLanguage;

  public DocumentTranslated(Object source, UUID correlationId, Document document, String translatedLanguage) {
    super(source, correlationId);
    this.document = document;
    this.translatedLanguage = translatedLanguage;
  }
  
  public Document getDocument() {
    return this.document;
  }

  public String getTranslatedLanguage() {
    return this.translatedLanguage;
  }
}
