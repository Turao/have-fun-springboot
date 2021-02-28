package com.arthur.springevents.card.integration.web;

import java.util.UUID;

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
    public String mockCreated() {
        var card = createCard.execute();
        return "Card created: " + card;
    }

    @GetMapping("/{id}")
    public String find(@PathVariable("id") UUID cardId) {
        var card = getCard.execute(cardId);
        return "Card: " + card;
    }

    @PostMapping
    public String create() {
        createCard.execute();
        return "Card created";
    }

    @DeleteMapping("/{id}")
    public String expire(@PathVariable("id") UUID cardId) {
        expireCard.execute(cardId);
        return "Card expired";
    }
}
