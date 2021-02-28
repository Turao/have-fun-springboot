package com.arthur.springevents.auction.repository;

import java.util.UUID;

import com.arthur.springevents.auction.domain.Auction;

import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<Auction, UUID> {}
