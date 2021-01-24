package com.arthur.springevents.domain.document;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.ToString;

@Entity
@ToString
public class Document {
    @Id private UUID id;

    private String text;

    private String language;

    public Document() {}

    public Document(UUID id, String text, String language) {
        this.id = id;
        this.text = text;
        this.language = language;
    }

    public UUID getId() {
        return this.id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
