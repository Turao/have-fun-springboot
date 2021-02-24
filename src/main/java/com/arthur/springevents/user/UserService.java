package com.arthur.springevents.user;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.arthur.springevents.user.events.UserCreated;
import com.arthur.springevents.user.events.UserDeleted;
import com.arthur.springevents.user.events.UserUpdated;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired private UserRepository repository;

    @Autowired private ApplicationEventPublisher eventPublisher;

    public User create() {
        log.info("Creating Document...");

        var user = new User(UUID.randomUUID());
        user = repository.save(user);
        log.info("User created: {}", user);
        
        eventPublisher.publishEvent(new UserCreated(this, user, UUID.randomUUID()));
        return user;
    }

    public User update(User user) {
        log.info("Updating user: {}", user);
        var updated = repository.save(user);
        log.info("User updated: {}", user);
        
        eventPublisher.publishEvent(new UserUpdated(this, updated, UUID.randomUUID()));
        return updated;
    }

    public User delete(UUID userId) {
        var user = repository.findById(userId)
            .orElseThrow(EntityNotFoundException::new);

        log.info("Deleting user: {}", userId);
        repository.deleteById(userId);
        log.info("User deleted: {}", userId);
        
        eventPublisher.publishEvent(new UserDeleted(this, user, UUID.randomUUID()));
        return user;
    }

    public User findById(UUID userId) {
        return repository.findById(userId)
            .orElseThrow(EntityNotFoundException::new);
    }
}
