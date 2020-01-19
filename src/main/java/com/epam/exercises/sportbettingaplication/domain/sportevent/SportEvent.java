package com.epam.exercises.sportbettingaplication.domain.sportevent;

import com.epam.exercises.sportbettingaplication.domain.bet.Bet;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public abstract class SportEvent {

  private Result result;
  private List<Bet> bets;
  private String title;
  private LocalDate startDate;
  private LocalDate endDate;
}
