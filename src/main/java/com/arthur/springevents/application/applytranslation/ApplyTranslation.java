package com.arthur.springevents.application.applytranslation;

import java.util.Collection;

import com.arthur.springevents.domain.translation.Translation;

public interface ApplyTranslation {
  void applyMany(Collection<Translation> translation);
  void applyOne(Translation translation);
}
