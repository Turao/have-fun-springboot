package com.arthur.springevents.domain.translation;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Translation {
    @Id private UUID id;

    private String originalText;
    private String translatedText;

    private String originalLanguage;
    private String translatedLanguage;

    public Translation() {}

    public Translation(
            UUID id,
            String originalText,
            String translatedText,
            String originalLanguage,
            String translatedLanguage) {
        this.id = id;
        this.originalText = originalText;
        this.translatedText = translatedText;
        this.originalLanguage = originalLanguage;
        this.translatedLanguage = translatedLanguage;
    }

    public UUID getId() {
        return this.id;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getOriginalText() {
        return this.originalText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslatedText() {
        return this.translatedText;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public void setTranslatedLanguage(String translatedLanguage) {
        this.translatedLanguage = translatedLanguage;
    }

    public String getTranslatedLanguage() {
        return this.translatedLanguage;
    }
}
