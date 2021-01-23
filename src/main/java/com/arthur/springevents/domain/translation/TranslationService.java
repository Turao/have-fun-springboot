package com.arthur.springevents.domain.translation;

import java.util.UUID;

import com.arthur.springevents.events.DomainModelUpdated;
import com.arthur.springevents.events.TranslationCandidatesGenerated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TranslationService {
  
  @Autowired
  private ApplicationEventPublisher eventPublisher;

  @Autowired
  private TranslationRepository repository;

  public TranslationService() {}

  @EventListener
  public void onEvent(TranslationCandidatesGenerated event) {
    log.info("Translation Candidates have been generated! Updating on database...");
    event.getCandidates()
      .stream()
      .map(this::update)
      .map(updated -> new DomainModelUpdated<Translation>(this, updated, event.getCorrelationId()))
      .forEach(eventPublisher::publishEvent);
  }

  public Translation create(
    String originalText,
    String translatedText,
    String originalLanguage,
    String translatedLanguage
  ) {
    log.info("Creating Translation...");

    var translation = new Translation(UUID.randomUUID(), originalText, translatedText, originalLanguage, translatedLanguage);
    translation = repository.save(translation);
    log.info("Translation created!");
    
    return translation;
  }

  public Translation update(Translation translation) {
    log.info("Updating Translation: {}", translation);
    repository.save(translation);
    log.info("Translation updated!");
    return translation;
  }
}
