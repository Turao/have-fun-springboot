package com.arthur.springevents.events;

import com.arthur.springevents.domain.translation.Translation;
import java.util.Collection;
import java.util.UUID;
import lombok.ToString;

@ToString
public class TranslationCandidatesGenerated extends EnhancedEvent {
    private static final long serialVersionUID = 1L;

    private Collection<Translation> candidates;

    public TranslationCandidatesGenerated(
            Object source, Collection<Translation> candidates, UUID correlationId) {
        super(source, correlationId);
        this.candidates = candidates;
    }

    public TranslationCandidatesGenerated(Object source, TranslationCandidatesGenerated event) {
        super(source);
        this.candidates = event.getCandidates();
    }

    public Collection<Translation> getCandidates() {
        return this.candidates;
    }
}
