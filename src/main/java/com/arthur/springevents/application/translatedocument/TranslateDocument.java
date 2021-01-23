package com.arthur.springevents.application.translatedocument;

import com.arthur.springevents.domain.document.Document;

public interface TranslateDocument {
  Document translate(Document document, String targetLanguage) throws InterruptedException;
}
