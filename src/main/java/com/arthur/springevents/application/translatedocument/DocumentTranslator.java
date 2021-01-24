package com.arthur.springevents.application.translatedocument;

import com.arthur.springevents.domain.document.Document;
import com.arthur.springevents.domain.document.DocumentService;
import com.arthur.springevents.events.DocumentTranslated;
import com.arthur.springevents.events.DocumentTranslationRequested;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DocumentTranslator implements TranslateDocument {

    // It is not possible to publish async events
    // by return value (using the @EventListener annotation)
    @Autowired private ApplicationEventPublisher eventPublisher;

    @Autowired private DocumentService documentService;

    @Async
    @EventListener
    public void onEvent(DocumentTranslationRequested event) throws InterruptedException {
        log.info("Received DocumentTranslationRequested event: {}", event);

        var translatedDocument = translate(event.getDocument(), event.getTargetLanguage());

        eventPublisher.publishEvent(
                new DocumentTranslated(
                        this,
                        event.getCorrelationId(),
                        translatedDocument,
                        event.getTargetLanguage()));
    }

    @Override
    public Document translate(Document document, String targetLanguage)
            throws InterruptedException {
        log.info("Translating Document: {}", document);

        // here we could retrieve all available translations and apply the best one
        documentService.create(document.getText() + " translated!", targetLanguage);

        Thread.sleep(5000);
        return document;
    }
}
