package com.arthur.springevents.match.integration.web;

import java.util.UUID;

import com.arthur.springevents.match.usecases.CreateMatch;
import com.arthur.springevents.match.usecases.EndMatch;
import com.arthur.springevents.match.usecases.StartMatch;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {

    private final CreateMatch createMatch;
    private final StartMatch startMatch;
    private final EndMatch endMatch;

    @PostMapping
    public String createMatch() {
        var match = createMatch.execute();
        return "Match Created: " + match;
    }

    @PostMapping("{id}/start")
    public String startMatch(@PathVariable("id") UUID id) {
        var match = startMatch.execute(id);
        return "Match Started: " + match;
    }

    @PostMapping("{id}/end")
    public String endMatch(@PathVariable("id") UUID id) {
        var match = endMatch.execute(id);
        return "Match Ended: " + match;
    }
}
