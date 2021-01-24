package com.arthur.springevents.application.generatetranslationcandidates;

import com.arthur.springevents.domain.document.Document;
import com.arthur.springevents.domain.translation.Translation;
import java.util.Collection;

public interface GenerateTranslationCandidates {
    Collection<Translation> generate(Document document, String targetLanguage);
}
