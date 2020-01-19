package com.epam.exercises.sportbettingaplication.service.impl;

import com.epam.exercises.sportbettingaplication.domain.bet.Bet;
import com.epam.exercises.sportbettingaplication.domain.outcome.Outcome;
import com.epam.exercises.sportbettingaplication.domain.outcome.OutcomeOdd;
import com.epam.exercises.sportbettingaplication.domain.sportevent.FootballSportEvent;
import com.epam.exercises.sportbettingaplication.domain.sportevent.SportEvent;
import com.epam.exercises.sportbettingaplication.domain.user.Player;
import com.epam.exercises.sportbettingaplication.domain.user.PlayerBuilder;
import com.epam.exercises.sportbettingaplication.enums.BetType;
import com.epam.exercises.sportbettingaplication.enums.Currency;
import com.epam.exercises.sportbettingaplication.service.DataPreparationService;

import java.math.BigDecimal;
import java.time.LocalDate;


public class DataPreparationServiceImpl implements DataPreparationService {


  @Override
  public OutcomeOdd prepareOutcomeOdd(Double value, LocalDate fromdate, LocalDate toDate) {
    OutcomeOdd outcomeOddForFirstOutcome = new OutcomeOdd();
    outcomeOddForFirstOutcome.setValue(value);
    outcomeOddForFirstOutcome.setFromDate(fromdate);
    outcomeOddForFirstOutcome.setToDate(toDate);
    return outcomeOddForFirstOutcome;
  }

  @Override
  public Outcome prepareOutcome(String value, OutcomeOdd outcomeOdd) {
    Outcome outcome = new Outcome();
    outcome.setOutcomeValue(value);
    outcome.addOutcomeOdd(outcomeOdd);
    return outcome;
  }

  @Override
  public Bet prepareBet(SportEvent sportEvent, BetType betType, Outcome outcome) {
    Bet bet = new Bet();
    bet.setSportEvent(sportEvent);
    bet.setBetType(betType);
    bet.addOutcome(outcome);
    return bet;
  }

  @Override
  public FootballSportEvent prepareFootballSportEvent(String title) {
    FootballSportEvent footballSportEvent = new FootballSportEvent();
    footballSportEvent.setTitle(title);
    return footballSportEvent;
  }

  @Override
  public Player createPlayer(String name, String accountNumber, BigDecimal balance,
                             Currency currency, LocalDate birth) {
    PlayerBuilder playerBuilder = new PlayerBuilder();
    playerBuilder.setName(name).setAccountNumber(accountNumber).setBalance(balance)
      .setCurrency(currency).setBirth(birth);
    return playerBuilder.createPlayer();
  }

}
