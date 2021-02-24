package com.arthur.springevents.user.integration.web;

import java.util.UUID;

import javax.websocket.server.PathParam;

import com.arthur.springevents.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping
    public String mockCreated() {
        var user = userService.create();
        return "User created: " + user;
    }

    @GetMapping("/{id}")
    public String find(@PathParam("id") UUID userId) {
        var user = userService.findById(userId);
        return "User: " + user;
    }

    @PostMapping
    public String create() {
        userService.create();
        return "User created";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathParam("id") UUID userId) {
        userService.delete(userId);
        return "User deleted";
    }
}
