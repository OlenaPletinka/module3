package com.epam.exercises.sportbettingaplication.enums;

public enum DataForBet {
  SPORT_EVENT("Arsenal vs Chelsea"),
  PLAYER("the player Oliver Giroud will score 1"),
  GOAL("the number of scored goals will be 3"),
  WINNER("the winner will be Arsenal");
  private String value;

  DataForBet(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
