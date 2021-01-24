package com.arthur.springevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync(proxyTargetClass = true)
@SpringBootApplication
public class SpringEventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEventsApplication.class, args);
    }
}
