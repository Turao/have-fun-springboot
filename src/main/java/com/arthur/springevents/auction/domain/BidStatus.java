package com.arthur.springevents.auction.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BidStatus {
  @JsonProperty("open")
  OPEN,

  @JsonProperty("winner")
  WINNER,

  @JsonProperty("loser")
  LOSER,
}
