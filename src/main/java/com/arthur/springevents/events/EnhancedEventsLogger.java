package com.arthur.springevents.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EnhancedEventsLogger {
  @EventListener
  public void onEvent(EnhancedEvent event) {
    log.info("An event has been published: {}", event);
  }
}
