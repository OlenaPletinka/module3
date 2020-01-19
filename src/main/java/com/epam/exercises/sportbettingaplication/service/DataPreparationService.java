package com.epam.exercises.sportbettingaplication.service;

import com.epam.exercises.sportbettingaplication.domain.bet.Bet;
import com.epam.exercises.sportbettingaplication.domain.outcome.Outcome;
import com.epam.exercises.sportbettingaplication.domain.outcome.OutcomeOdd;
import com.epam.exercises.sportbettingaplication.domain.sportevent.FootballSportEvent;
import com.epam.exercises.sportbettingaplication.domain.sportevent.SportEvent;
import com.epam.exercises.sportbettingaplication.domain.user.Player;
import com.epam.exercises.sportbettingaplication.enums.BetType;
import com.epam.exercises.sportbettingaplication.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface DataPreparationService {

  OutcomeOdd prepareOutcomeOdd(Double value, LocalDate fromdate, LocalDate toDate);

  Outcome prepareOutcome(String value, OutcomeOdd outcomeOdd);

  Bet prepareBet(SportEvent sportEvent, BetType betType, Outcome outcome);

  FootballSportEvent prepareFootballSportEvent(String title);

  Player createPlayer(String name, String accountNumber, BigDecimal balance, Currency currency,
                      LocalDate birth);
}
