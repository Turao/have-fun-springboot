package com.arthur.springevents.application.generatetranslationcandidates;

import java.util.Collection;

import com.arthur.springevents.domain.document.Document;
import com.arthur.springevents.domain.translation.Translation;

public interface GenerateTranslationCandidates {
  Collection<Translation> generate(Document document, String targetLanguage);
}
