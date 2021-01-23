package com.arthur.springevents.events;

import java.util.UUID;

public class DomainModelUpdated<T> extends EnhancedEvent {

  private static final long serialVersionUID = 1L;
  
  private T model;

  public DomainModelUpdated(Object source, T model, UUID correlationId) {
    super(source, correlationId);
    this.model = model;
  }
  
  public T getModel() {
    return this.model;
  }
}
