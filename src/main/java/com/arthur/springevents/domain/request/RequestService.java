package com.arthur.springevents.domain.request;

import com.arthur.springevents.domain.document.Document;
import java.util.Collection;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestService {
    @Autowired private RequestRepository repository;

    public Request create(Document document, String targetLanguage) {
        log.info("Creating Request...");

        var request =
                new Request(UUID.randomUUID(), document, document.getLanguage(), targetLanguage);
        request = repository.save(request);

        log.info("Request created: {}", request);
        return request;
    }

    public Collection<Request> findAll() {
        return repository.findAll();
    }
}
