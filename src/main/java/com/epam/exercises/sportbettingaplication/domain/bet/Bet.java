package com.epam.exercises.sportbettingaplication.domain.bet;

import com.epam.exercises.sportbettingaplication.domain.outcome.Outcome;
import com.epam.exercises.sportbettingaplication.domain.sportevent.SportEvent;
import com.epam.exercises.sportbettingaplication.enums.BetType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Bet {

  private SportEvent sportEvent;
  private List<Outcome> outcomes = new ArrayList<>();
  private String description;
  private BetType betType;

  public void addOutcome(Outcome outcome) {
    outcomes.add(outcome);
  }
}
