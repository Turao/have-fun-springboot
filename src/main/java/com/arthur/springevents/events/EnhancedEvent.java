package com.arthur.springevents.events;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import lombok.ToString;

@ToString
public abstract class EnhancedEvent extends ApplicationEvent {
  
  private static final long serialVersionUID = 1L;
  
  private UUID correlationId;

  public EnhancedEvent(Object source) {
    super(source);
    this.correlationId = UUID.randomUUID();
  }

  public EnhancedEvent(Object source, UUID correlationId) {
    super(source);
    this.correlationId = correlationId;
  }

  public EnhancedEvent(Object source, EnhancedEvent event) {
    super(source);
    this.correlationId = event.getCorrelationId();
  }

  public UUID getCorrelationId() {
    return this.correlationId;
  }
}
