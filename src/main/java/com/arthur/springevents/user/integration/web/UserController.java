package com.arthur.springevents.user.integration.web;

import java.util.UUID;

import com.arthur.springevents.user.domain.User;
import com.arthur.springevents.user.usecases.CreateUser;
import com.arthur.springevents.user.usecases.DeleteUser;
import com.arthur.springevents.user.usecases.GetUser;
import com.arthur.springevents.user.usecases.UpdateUser;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUser createUser;
    private final UpdateUser updateUser;
    private final DeleteUser deleteUser;
    private final GetUser getUser;

    @GetMapping
    public User mockCreated() {
        var user = createUser.execute();
        return user;
    }

    @GetMapping("/{id}")
    public User find(@PathVariable("id") UUID userId) {
        var user = getUser.execute(userId);
        return user;
    }

    @PostMapping
    public User create() {
        var user = createUser.execute();
        return user;
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable("id") UUID userId) {
        var user = deleteUser.execute(userId);
        return user;
    }
}
