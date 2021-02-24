package com.arthur.springevents.core.events;

import java.util.UUID;

public class DomainModelEvent<T> extends EnhancedEvent {

    private static final long serialVersionUID = 1L;

    private T model;

    public DomainModelEvent(Object source, T model, UUID correlationId) {
        super(source, correlationId);
        this.model = model;
    }

    public T getModel() {
        return this.model;
    }
}
