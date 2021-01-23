package com.arthur.springevents.domain.request;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.arthur.springevents.domain.document.Document;

import lombok.ToString;

@ToString
@Entity
public class Request {
  @Id
  private UUID id;

  @ManyToOne
  private Document document;
  private String targetLanguage;

  public Request() {}

  public Request(
    UUID id,
    Document document,
    String originalLanguage,
    String targetLanguage
  ) {
    this.id = id;
    this.document = document;
    this.targetLanguage = targetLanguage;
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

  public String getTargetLanguage() {
    return this.targetLanguage;
  }

  public void setTargetLanguage(String targetLanguage) {
    this.targetLanguage = targetLanguage;
  }
}
