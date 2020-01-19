package com.epam.exercises.sportbettingaplication.ui.impl;

import com.epam.exercises.sportbettingaplication.domain.OutputBetObject;
import com.epam.exercises.sportbettingaplication.domain.outcome.Outcome;
import com.epam.exercises.sportbettingaplication.domain.outcome.OutcomeOdd;
import com.epam.exercises.sportbettingaplication.domain.user.Player;
import com.epam.exercises.sportbettingaplication.domain.wager.Wager;
import com.epam.exercises.sportbettingaplication.domain.wager.WagerBuilder;
import com.epam.exercises.sportbettingaplication.service.DataProcessorService;
import com.epam.exercises.sportbettingaplication.service.EventLoger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.exercises.sportbettingaplication.enums.Currency.USD;
import static com.epam.exercises.sportbettingaplication.enums.DataForBet.*;
import static com.epam.exercises.sportbettingaplication.enums.OutcomeOddValue.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OutputServiceImplTest {
  private static final LocalDate FROM_DATE = LocalDate.of(2016, 2, 3);
  private static final LocalDate TO_DATE = LocalDate.of(2016, 2, 5);
  private static final String PLAYER_NAME = "Alex";
  private static final String ACCOUNT = "123456";
  private static final BigDecimal AMOUNT = BigDecimal.valueOf(1000);
  private static final LocalDate BIRTH = LocalDate.of(2000, 10, 10);
  private static final Integer TIME_STEMP = 5;
  private static final String BALLANCE_INPUT = "10000";
  private static final BigDecimal BALLANCE = BigDecimal.valueOf(10000);
  private static final boolean IS_PROCESSED = false;
  private static final boolean IS_WIN = false;

  @InjectMocks
  private OutputServiceImpl outputService;
  @Mock
  private EventLoger eventLoger;
  @Mock
  private DataProcessorService dataProcessorService;
  @Mock
  private InputValidatorImpl inputValidator;
  @Mock
  private BufferedReader bufferedReader;
  @Mock
  private WagerBuilder wagerBuilder;

  @Test
  public void getBetsOutputTest() {
    //given
    List<OutputBetObject> outputBetObjects = buildOutputBetObjects();
    when(dataProcessorService.createOutputBetObjects()).thenReturn(outputBetObjects);
    doNothing().when(eventLoger).logEvent(any(String.class));
    //when
    List<OutputBetObject> actualResult = outputService.getBetsOutput();
    //then
    assertEquals(outputBetObjects, actualResult);
    verify(eventLoger, times(3)).logEvent(any(String.class));

  }

  @Test
  public void getResultOutputTest() {
    //given
    doNothing().when(eventLoger).logEvent(any(String.class));
    //when
    outputService.getResultOutput(buildOutcome(PLAYER.getValue()), buildWager());
    //then
    verify(eventLoger, times(3)).logEvent(any(String.class));

  }


  @Test
  public void makeBetTest() throws IOException {
    //given
    when(dataProcessorService.createOutputBetObjects()).thenReturn(buildOutputBetObjects());
    when(bufferedReader.readLine()).thenReturn("1").thenReturn("100").thenReturn("q");
    doNothing().when(inputValidator).validateFirstBetOn(any(String.class));
    doNothing().when(eventLoger).logEvent(any(String.class));
    when(wagerBuilder.createWager()).thenReturn(buildWager());
    //when
    outputService.makeBet(bufferedReader, buildPlayer());
    //then
    verify(eventLoger, times(11)).logEvent(any(String.class));
  }

  @Test
  public void getPlayerNameTest() throws IOException {
    //given
    doNothing().when(eventLoger).logEvent("Hi, what is your name?");
    when(bufferedReader.readLine()).thenReturn(PLAYER_NAME);
    doNothing().when(inputValidator).validateName(PLAYER_NAME);
    //when
    String actualResult = outputService.getPlayerName(bufferedReader);
    //then
    assertEquals(PLAYER_NAME, actualResult);
  }

  @Test
  public void getAccountNumberTest() throws IOException {
    //given
    doNothing().when(eventLoger).logEvent("What is your account number?");
    when(bufferedReader.readLine()).thenReturn(ACCOUNT);
    doNothing().when(inputValidator).validateName(ACCOUNT);
    //when
    String actualResult = outputService.getAccountNumber(bufferedReader);
    //then
    assertEquals(actualResult, ACCOUNT);
  }

  @Test
  public void getBalanceInputTest() throws IOException {
    //given
    doNothing().when(eventLoger).logEvent("How much money do you have (more than 0)?");
    when(bufferedReader.readLine()).thenReturn(BALLANCE_INPUT);
    doNothing().when(inputValidator).validateBalanceInput(BALLANCE_INPUT);
    //when
    String actualResult = outputService.getBalanceInput(bufferedReader);
    //then
    assertEquals(actualResult, BALLANCE_INPUT);
  }

  @Test
  public void getCurrencyInput() throws IOException {
    //given
    doNothing().when(eventLoger).logEvent("What is your currency? (UAH, EUR or USD)");
    when(bufferedReader.readLine()).thenReturn(USD.getValue());
    doNothing().when(inputValidator).validateBalanceInput(USD.getValue());
    //when
    String actualResult = outputService.getCurrencyInput(bufferedReader);
    //then
    assertEquals(actualResult, USD.getValue());
  }

  @Test
  public void getBirthInput() throws IOException {
    //given
    doNothing().when(eventLoger).logEvent("When were you born? eg.:1990-02-03");
    String birth = BIRTH.toString();
    when(bufferedReader.readLine()).thenReturn(birth);
    doNothing().when(inputValidator).validateBirthInput(birth);
    //when
    String actualResult = outputService.getBirthInput(bufferedReader);
    //then
    assertEquals(actualResult, birth);
  }

  private List<OutputBetObject> buildOutputBetObjects() {
    List<OutputBetObject> outputBetObjects = new ArrayList<>();

    Outcome outcome = buildOutcome(PLAYER.getValue());
    OutcomeOdd outcomeOdd = buildOutcomeOdd(OUTCOME_ODD_VALUE_10_0.getValue());
    OutputBetObject betObjectFirst = buildBetObject(outcome, outcomeOdd);

    outputBetObjects.add(betObjectFirst);

    Outcome outcomeSecond = buildOutcome(GOAL.getValue());
    OutcomeOdd outcomeOddSecond = buildOutcomeOdd(OUTCOME_ODD_VALUE_1_3.getValue());
    OutputBetObject betObjectSecond = buildBetObject(outcomeSecond, outcomeOddSecond);

    outputBetObjects.add(betObjectSecond);

    return outputBetObjects;
  }

  private OutputBetObject buildBetObject(Outcome outcome, OutcomeOdd outcomeOdd) {
    return new OutputBetObject(SPORT_EVENT.getValue(), outcome, outcomeOdd, FROM_DATE, TO_DATE);
  }

  private OutcomeOdd buildOutcomeOdd(Double value) {
    OutcomeOdd outcomeOdd = new OutcomeOdd();
    outcomeOdd.setValue(value);
    return outcomeOdd;
  }

  private Outcome buildOutcome(String value) {
    Outcome outcome = new Outcome();
    outcome.setOutcomeValue(value);
    return outcome;
  }

  private Wager buildWager() {
    return new Wager(buildOutcomeOdd(OUTCOME_ODD_VALUE_4_0.getValue()), buildPlayer(), AMOUNT, USD,
              TIME_STEMP, IS_PROCESSED, IS_WIN);
  }

  private Player buildPlayer() {
    return new Player(PLAYER_NAME, ACCOUNT, BALLANCE, USD, BIRTH);
  }

}
