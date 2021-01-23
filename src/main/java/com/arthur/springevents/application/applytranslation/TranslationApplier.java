package com.arthur.springevents.application.applytranslation;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import com.arthur.springevents.domain.document.Document;
import com.arthur.springevents.domain.document.DocumentService;
import com.arthur.springevents.domain.translation.Translation;
import com.arthur.springevents.events.DomainModelUpdated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TranslationApplier implements ApplyTranslation {

  @Autowired
  private DocumentService documentService;

  @EventListener
  public void onEvent(DomainModelUpdated<Translation> event) {
    log.info("A Translation has been updated and needs to be re-applied...");
    applyOne(event.getModel());
  }

  @Override
  public void applyMany(Collection<Translation> translation) {
    translation.forEach(this::applyOne);
  }

  @Override
  public void applyOne(Translation translation) {
    log.info("Applying Translation: {}", translation);

    // here we could find all documents that have been translated to a target language
    var affectedDocuments = documentService.findAllContainingAndLanguage(translation.getOriginalText(), translation.getTranslatedLanguage());

    affectedDocuments.forEach(document -> {
      log.info("Applying Translation: {} to Document: {}", translation, document);
      var originalText = document.getText();
      var translatedText = originalText.replace(translation.getOriginalText(), translation.getTranslatedText());
      document.setText(translatedText);
      documentService.update(document);
    });
  }
  
}
