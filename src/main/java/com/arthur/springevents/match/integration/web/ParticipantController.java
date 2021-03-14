package com.arthur.springevents.match.integration.web;

import java.util.UUID;

import com.arthur.springevents.match.domain.Participant;
import com.arthur.springevents.match.usecases.JoinMatch;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/participant")
@RequiredArgsConstructor
public class ParticipantController {

    private final JoinMatch joinMatch;

    @PostMapping("/{participantId}/join/{matchId}")
    public Participant joinMatch(
        @PathVariable("participantId") UUID participantId,
        @PathVariable("matchId") UUID matchId)
    {
        var participant = joinMatch.execute(participantId, matchId);
        return participant;
    }
}
