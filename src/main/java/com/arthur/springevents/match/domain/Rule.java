package com.arthur.springevents.match.domain;

public interface Rule {
  void check(Match match) throws IllegalStateException;
}
