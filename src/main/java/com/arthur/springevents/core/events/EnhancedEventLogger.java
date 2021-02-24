package com.arthur.springevents.core.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnhancedEventLogger {
    @EventListener
    public void onEvent(EnhancedEvent event) {
        log.info("An event has been published: {}", event);
    }
}
