package com.arthur.springevents.match.integration.web;

import java.util.UUID;

import javax.websocket.server.PathParam;

import com.arthur.springevents.match.MatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired private MatchService matchService;

    @GetMapping
    public String mockCreated() {
        var match = matchService.create();
        return "Match created: " + match;
    }

    @GetMapping("/{id}")
    public String find(@PathParam("id") UUID matchId) {
        var match = matchService.findById(matchId);
        return "Match: " + match;
    }

    @PostMapping
    public String create() {
        matchService.create();
        return "Match created";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathParam("id") UUID matchId) {
        matchService.delete(matchId);
        return "Match deleted";
    }
}
