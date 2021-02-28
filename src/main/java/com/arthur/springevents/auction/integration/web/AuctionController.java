package com.arthur.springevents.auction.integration.web;

import java.util.UUID;

import com.arthur.springevents.auction.usecases.CreateAuction;
import com.arthur.springevents.auction.usecases.EndAuction;
import com.arthur.springevents.auction.usecases.StartAuction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired private CreateAuction createAuction;
    @Autowired private StartAuction startAuction;
    @Autowired private EndAuction endAuction;

    @PostMapping
    public String createAuction() {
        var auction = createAuction.execute();
        return "Auction Created: " + auction;
    }

    @PostMapping("{id}/start")
    public String startAuction(@PathVariable("id") UUID id) {
        var auction = startAuction.execute(id);
        return "Auction Started: " + auction;
    }

    @PostMapping("{id}/end")
    public String endAuction(@PathVariable("id") UUID id) {
        var auction = endAuction.execute(id);
        return "Auction Ended: " + auction;
    }
}
