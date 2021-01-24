package com.arthur.springevents.application.generatetranslationcandidates;

import com.arthur.springevents.domain.document.Document;
import com.arthur.springevents.domain.translation.Translation;
import com.arthur.springevents.domain.translation.TranslationService;
import com.arthur.springevents.events.DocumentTranslated;
import com.arthur.springevents.events.TranslationCandidatesGenerated;
import java.util.Collection;
import java.util.HashSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Lazy(false)
@Service
public class TranslationCandidatesGenerator implements GenerateTranslationCandidates {

    @Autowired private TranslationService translationService;

    @EventListener
    public TranslationCandidatesGenerated onEvent(DocumentTranslated event) {
        log.info("Received DocumentTranslated event: {}", event);

        var candidates = generate(event.getDocument(), event.getTranslatedLanguage());

        return new TranslationCandidatesGenerated(this, candidates, event.getCorrelationId());
    }

    @Override
    public Collection<Translation> generate(Document document, String targetLanguage) {
        log.info("Generating Translation Candidates for Document: {}", document);

        // say we want to update our db with some translated sentences...
        // split the document by sentence, and create a translation candidate from it
        var candidates = new HashSet<Translation>();
        for (String token :
                document.getText()
                        .split(" ")) { // * yeah, it's splitting by words, but who cares...
            var translatedToken = new StringBuilder(token).reverse().toString();
            var translationCandidate =
                    translationService.create(
                            token, translatedToken, document.getLanguage(), targetLanguage);
            candidates.add(translationCandidate);
        }

        log.info("Translation Candidates generated: {}", candidates);
        return candidates;
    }
}
