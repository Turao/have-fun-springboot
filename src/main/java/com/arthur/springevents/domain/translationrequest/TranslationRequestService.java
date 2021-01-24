package com.arthur.springevents.domain.translationrequest;

import com.arthur.springevents.domain.document.Document;
import java.util.Collection;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TranslationRequestService {
    @Autowired private TranslationRequestRepository repository;

    public TranslationRequest create(Document document, Collection<String> targetLanguages) {
        log.info("Creating Request...");

        var request =
                new TranslationRequest(
                        UUID.randomUUID(), document, document.getLanguage(), targetLanguages);
        request = repository.save(request);

        log.info("Request created: {}", request);
        return request;
    }

    public Collection<TranslationRequest> findAll() {
        return repository.findAll();
    }
}
