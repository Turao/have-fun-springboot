package com.arthur.springevents.card.integration.web;

import java.util.UUID;

import com.arthur.springevents.card.domain.Card;
import com.arthur.springevents.card.usecases.CreateCard;
import com.arthur.springevents.card.usecases.ExpireCard;
import com.arthur.springevents.card.usecases.GetCard;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {

    private final CreateCard createCard;
    private final GetCard getCard;
    private final ExpireCard expireCard;

    @GetMapping
    public Card mockCreated() {
        var card = createCard.execute();
        return card;
    }

    @GetMapping("/{id}")
    public Card find(@PathVariable("id") UUID cardId) {
        var card = getCard.execute(cardId);
        return card;
    }

    @PostMapping
    public Card create() {
        var card = createCard.execute();
        return card;
    }

    @DeleteMapping("/{id}")
    public Card expire(@PathVariable("id") UUID cardId) {
        var card = expireCard.execute(cardId);
        return card;
    }
}
