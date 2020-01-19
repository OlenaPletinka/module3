package com.epam.exercises.sportbettingaplication.domain;

import com.epam.exercises.sportbettingaplication.domain.outcome.Outcome;
import com.epam.exercises.sportbettingaplication.domain.outcome.OutcomeOdd;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OutputBetObject {

   private String sportEventTitle;
   private Outcome outcome;
   private String outcomeValue;
   private Double outcomeOddValue;
   private OutcomeOdd outcomeOdd;
   private LocalDate fromDate;
   private LocalDate toDate;

   public OutputBetObject(String sportEventTitle, Outcome outcome, OutcomeOdd outcomeOdd,
       LocalDate fromDate, LocalDate toDate) {
      this.sportEventTitle = sportEventTitle;
      this.outcome = outcome;
      this.outcomeValue = outcome.getOutcomeValue();
      this.outcomeOdd = outcomeOdd;
      this.outcomeOddValue = outcomeOdd.getValue();
      this.fromDate = fromDate;
      this.toDate = toDate;
   }
}
