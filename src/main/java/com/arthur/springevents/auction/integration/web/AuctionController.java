package com.arthur.springevents.auction.integration.web;

import java.util.UUID;

import com.arthur.springevents.auction.domain.Auction;
import com.arthur.springevents.auction.usecases.CreateAuction;
import com.arthur.springevents.auction.usecases.EndAuction;
import com.arthur.springevents.auction.usecases.StartAuction;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionController {

    private final CreateAuction createAuction;
    private final StartAuction startAuction;
    private final EndAuction endAuction;

    @PostMapping
    public Auction createAuction() {
        var auction = createAuction.execute();
        return auction;
    }

    @PostMapping("{id}/start")
    public Auction startAuction(@PathVariable("id") UUID id) {
        var auction = startAuction.execute(id);
        return auction;
    }

    @PostMapping("{id}/end")
    public Auction endAuction(@PathVariable("id") UUID id) {
        var auction = endAuction.execute(id);
        return auction;
    }
}
