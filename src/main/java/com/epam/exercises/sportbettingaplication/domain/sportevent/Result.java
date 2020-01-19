package com.epam.exercises.sportbettingaplication.domain.sportevent;

import com.epam.exercises.sportbettingaplication.domain.outcome.Outcome;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Result {

  private List<Outcome> outcomes = new ArrayList<>();

  public void addOutcomes(Outcome outcome) {
    outcomes.add(outcome);
  }
}
