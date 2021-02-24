package com.arthur.springevents.card.integration.web;

import java.util.UUID;

import javax.websocket.server.PathParam;

import com.arthur.springevents.card.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired private CardService cardService;

    @GetMapping
    public String mockCreated() {
        var card = cardService.create();
        return "Card created: " + card;
    }

    @GetMapping("/{id}")
    public String find(@PathParam("id") UUID cardId) {
        var card = cardService.findById(cardId);
        return "Card: " + card;
    }

    @PostMapping
    public String create() {
        cardService.create();
        return "Card created";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathParam("id") UUID cardId) {
        cardService.delete(cardId);
        return "Card deleted";
    }
}
