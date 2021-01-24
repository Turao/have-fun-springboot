package com.arthur.springevents.application.applytranslation;

import com.arthur.springevents.domain.translation.Translation;
import java.util.Collection;

public interface ApplyTranslation {
    void applyMany(Collection<Translation> translation);

    void applyOne(Translation translation);
}
