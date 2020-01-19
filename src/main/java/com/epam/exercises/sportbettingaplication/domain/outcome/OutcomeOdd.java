package com.epam.exercises.sportbettingaplication.domain.outcome;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OutcomeOdd {

  private Outcome outcome;
  private Double value;
  private LocalDate fromDate;
  private LocalDate toDate;
}
