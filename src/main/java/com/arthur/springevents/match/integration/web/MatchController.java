package com.arthur.springevents.match.integration.web;

import java.util.UUID;

import com.arthur.springevents.match.domain.Match;
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
    public Match createMatch() {
        var match = createMatch.execute();
        return match;
    }

    @PostMapping("{id}/start")
    public Match startMatch(@PathVariable("id") UUID id) {
        var match = startMatch.execute(id);
        return match;
    }

    @PostMapping("{id}/end")
    public Match endMatch(@PathVariable("id") UUID id) {
        var match = endMatch.execute(id);
        return match;
    }
}
