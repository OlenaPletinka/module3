package com.epam.exercises.sportbettingaplication.service.impl;

import com.epam.exercises.sportbettingaplication.domain.bet.Bet;
import com.epam.exercises.sportbettingaplication.domain.outcome.Outcome;
import com.epam.exercises.sportbettingaplication.domain.outcome.OutcomeOdd;
import com.epam.exercises.sportbettingaplication.domain.sportevent.FootballSportEvent;
import com.epam.exercises.sportbettingaplication.domain.user.Player;
import com.epam.exercises.sportbettingaplication.enums.BetType;
import com.epam.exercises.sportbettingaplication.enums.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DataPreparationServiceImplTest {

  private final LocalDate fromdate = LocalDate.of(2016, 2, 3);
  private final LocalDate toDate = LocalDate.of(2016, 2, 5);
  private final double value = 2.00;
  private final String outcomeValue = "the player Oliver Giroud will score 1";
  private final String footballSportEventTitle = "Arsenal vs Chelsea";
  private final String userName = "Alex";
  private final String accountNumber = "123456";
  private  final BigDecimal balance = BigDecimal.valueOf(12345678);
  private final Currency currency = Currency.USD;
  private final LocalDate birth = LocalDate.of(1980, 10, 10);

  @InjectMocks
  private DataPreparationServiceImpl dataPreparationService;

  @Test
  public void prepareOutcomeOddTest() {
    // when
    OutcomeOdd actualResult = dataPreparationService.prepareOutcomeOdd(value, fromdate, toDate);
    //then
    assertEquals(buildOutcomeOdd(), actualResult);
  }

  @Test
  public void prepareOutcomeTest() {
    //when
    Outcome actualResult = dataPreparationService.prepareOutcome(outcomeValue, buildOutcomeOdd());
    //then
    assertEquals(buildOutcome(), actualResult);
  }


  @Test
  public void prepareBetTest() {
    //when
    Bet actualResult = dataPreparationService.prepareBet(new FootballSportEvent(), BetType.BETTING_FOR_GOALS, buildOutcome());
    //then
    assertEquals(buildBet(), actualResult);
  }


  @Test
  public void prepareFootballSportEvent() {
    //when
    FootballSportEvent actualResult = dataPreparationService.prepareFootballSportEvent(footballSportEventTitle);
    //then
    assertEquals(buildFootballsportEvent(), actualResult);
  }


  @Test
  public void createPlayer() {
    //when
    Player actualResult = dataPreparationService.createPlayer(userName, accountNumber, balance, currency, birth);
    //then
    assertEquals(buildPlayer(), actualResult);
  }


  private OutcomeOdd buildOutcomeOdd() {
    OutcomeOdd outcomeOdd = new OutcomeOdd();
    outcomeOdd.setValue(value);
    outcomeOdd.setToDate(toDate);
    outcomeOdd.setFromDate(fromdate);
    return outcomeOdd;
  }

  private Outcome buildOutcome() {
    Outcome outcome = new Outcome();
    outcome.setOutcomeValue(outcomeValue);
    outcome.addOutcomeOdd(buildOutcomeOdd());
    return outcome;
  }

  private Bet buildBet() {
    Bet bet = new Bet();
    bet.setBetType(BetType.BETTING_FOR_GOALS);
    bet.setSportEvent(new FootballSportEvent());
    bet.addOutcome(buildOutcome());
    return bet;
  }

  private FootballSportEvent buildFootballsportEvent() {
    FootballSportEvent footballSportEvent = new FootballSportEvent();
    footballSportEvent.setTitle(footballSportEventTitle);
    return footballSportEvent;
  }

  private Player buildPlayer() {
    return new Player(userName, accountNumber, balance, currency, birth);
  }
}