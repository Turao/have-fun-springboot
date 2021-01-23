package com.arthur.springevents.events;

import java.util.UUID;

import com.arthur.springevents.domain.document.Document;
import com.arthur.springevents.domain.request.Request;

import lombok.ToString;

@ToString
public class DocumentTranslationRequested extends EnhancedEvent {
  private static final long serialVersionUID = 1L;
  
  private Document document;
  private String targetLanguage;

  public DocumentTranslationRequested(Object source, UUID correlationId, Document document, String targetLanguage) {
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
