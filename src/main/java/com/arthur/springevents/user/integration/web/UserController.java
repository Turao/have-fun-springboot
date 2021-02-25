package com.arthur.springevents.user.integration.web;

import java.util.UUID;

import com.arthur.springevents.user.usecases.CreateUser;
import com.arthur.springevents.user.usecases.DeleteUser;
import com.arthur.springevents.user.usecases.GetUser;
import com.arthur.springevents.user.usecases.UpdateUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private CreateUser createUser;
    @Autowired private UpdateUser updateUser;
    @Autowired private DeleteUser deleteUser;
    @Autowired private GetUser getUser;

    @GetMapping
    public String mockCreated() {
        var user = createUser.execute();
        return "User created: " + user;
    }

    @GetMapping("/{id}")
    public String find(@PathVariable("id") UUID userId) {
        var user = getUser.execute(userId);
        return "User: " + user;
    }

    @PostMapping
    public String create() {
        createUser.execute();
        return "User created";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID userId) {
        deleteUser.execute(userId);
        return "User deleted";
    }
}
