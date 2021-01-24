package com.arthur.springevents.integration.web;

import com.arthur.springevents.domain.document.DocumentService;
import com.arthur.springevents.domain.request.RequestService;
import com.arthur.springevents.events.DocumentTranslationRequested;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/translation")
public class TranslationController {

    @Autowired private ApplicationEventPublisher publisher;

    @Autowired private RequestService requestService;

    @Autowired private DocumentService documentService;

    @GetMapping
    public String onRequest() {
        var document = documentService.create("this is my document\'s text", "en-US");

        var translationRequest = requestService.create(document, "pt-BR");

        var event =
                new DocumentTranslationRequested(
                        this,
                        translationRequest.getId(),
                        translationRequest.getDocument(),
                        translationRequest.getTargetLanguage());

        publisher.publishEvent(event);

        log.info("Translation request sent!");
        return "Document will be be translated - Request ID: " + translationRequest.getId();
    }
}
