package com.epam.exercises.sportbettingaplication.enums;

public enum BetType {

  BETTING_FOR_GOALS("betting for goals"), WINNER("winner"), PLAYERS_SCORE("player’s score");
  private String type;

  BetType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
