package com.epam.exercises.sportbettingaplication.service.impl;

import com.epam.exercises.sportbettingaplication.domain.OutputBetObject;
import com.epam.exercises.sportbettingaplication.domain.bet.Bet;
import com.epam.exercises.sportbettingaplication.domain.outcome.Outcome;
import com.epam.exercises.sportbettingaplication.domain.outcome.OutcomeOdd;
import com.epam.exercises.sportbettingaplication.domain.sportevent.FootballSportEvent;
import com.epam.exercises.sportbettingaplication.domain.sportevent.SportEvent;
import com.epam.exercises.sportbettingaplication.enums.BetType;
import com.epam.exercises.sportbettingaplication.service.DataPreparationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.exercises.sportbettingaplication.enums.DataForBet.*;
import static com.epam.exercises.sportbettingaplication.enums.OutcomeOddValue.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataProcessorServiceImplTest {
  private LocalDate fromdate = LocalDate.of(2016, 2, 3);
  private LocalDate toDate = LocalDate.of(2016, 2, 5);

  @InjectMocks
  private DataProcessorServiceImpl dataProcessorService;
  @Mock
  private DataPreparationService dataPreparationService;

  @Test
  public void createOutputBetObjectsTest() {
    //given
    when(dataPreparationService
              .prepareFootballSportEvent(SPORT_EVENT.getValue())).thenReturn(buildFootballSportEvent());

    when(dataPreparationService
              .prepareOutcomeOdd(eq(OUTCOME_ODD_VALUE_10_0.getValue()), any(LocalDate.class), any(LocalDate.class))).thenReturn(buildOutcomeOdd(OUTCOME_ODD_VALUE_10_0.getValue()));
    when(dataPreparationService.prepareOutcome(eq(PLAYER.getValue()), any(OutcomeOdd.class))).thenReturn(buildOutcome(PLAYER.getValue(), OUTCOME_ODD_VALUE_10_0.getValue()));
    when(dataPreparationService.prepareBet(any(SportEvent.class), any(BetType.class), any(Outcome.class))).thenReturn(new Bet());

    when(dataPreparationService
              .prepareOutcomeOdd(eq(OUTCOME_ODD_VALUE_1_3.getValue()), any(LocalDate.class), any(LocalDate.class))).thenReturn(buildOutcomeOdd(OUTCOME_ODD_VALUE_1_3.getValue()));
    when(dataPreparationService.prepareOutcome(eq(GOAL.getValue()), any(OutcomeOdd.class))).thenReturn(buildOutcome(GOAL.getValue(), OUTCOME_ODD_VALUE_1_3.getValue()));
    when(dataPreparationService.prepareBet(any(SportEvent.class), any(BetType.class), any(Outcome.class))).thenReturn(new Bet());

    when(dataPreparationService
              .prepareOutcomeOdd(eq(OUTCOME_ODD_VALUE_4_0.getValue()), any(LocalDate.class), any(LocalDate.class))).thenReturn(buildOutcomeOdd(OUTCOME_ODD_VALUE_4_0.getValue()));
    when(dataPreparationService.prepareOutcome(eq(WINNER.getValue()), any(OutcomeOdd.class))).thenReturn(buildOutcome(WINNER.getValue(), OUTCOME_ODD_VALUE_4_0.getValue()));
    when(dataPreparationService.prepareBet(any(SportEvent.class), any(BetType.class), any(Outcome.class))).thenReturn(new Bet());
    //when
    List<OutputBetObject> actualResult = dataProcessorService.createOutputBetObjects();
    //then
    assertEquals(buildOutputBetObjects(), actualResult);

  }

  private FootballSportEvent buildFootballSportEvent() {
    FootballSportEvent footballSportEvent = new FootballSportEvent();
    footballSportEvent.setTitle(SPORT_EVENT.getValue());
    return footballSportEvent;
  }

  private List<OutputBetObject> buildOutputBetObjects() {
    List<OutputBetObject> outputBetObjects = new ArrayList<>();
    FootballSportEvent footballSportEvent = buildFootballSportEvent();
    outputBetObjects.add(
              buildOutputBetObject(footballSportEvent, PLAYER.getValue(),
                        OUTCOME_ODD_VALUE_10_0.getValue()));
    outputBetObjects.add(
              buildOutputBetObject(footballSportEvent, GOAL.getValue(), OUTCOME_ODD_VALUE_1_3.getValue()));
    outputBetObjects.add(
              buildOutputBetObject(footballSportEvent, WINNER.getValue(), OUTCOME_ODD_VALUE_4_0.getValue()));
    return outputBetObjects;
  }

  private OutputBetObject buildOutputBetObject(SportEvent sportEvent, String outcomeValue,
                                               Double outcomeOddValue) {
    Outcome outcome = buildOutcome(outcomeValue, outcomeOddValue);
    OutcomeOdd outcomeOdd = buildOutcomeOdd(outcomeOddValue);
    outcomeOdd.setOutcome(outcome);
    return new OutputBetObject(sportEvent.getTitle(), buildOutcome(outcomeValue, outcomeOddValue),
              outcomeOdd, fromdate, toDate);
  }

  private OutcomeOdd buildOutcomeOdd(Double value) {
    OutcomeOdd outcomeOdd = new OutcomeOdd();
    outcomeOdd.setValue(value);
    outcomeOdd.setFromDate(fromdate);
    outcomeOdd.setToDate(toDate);
    return outcomeOdd;
  }

  private Outcome buildOutcome(String outcomeValue, Double value) {
    Outcome outcome = new Outcome();
    outcome.setOutcomeValue(outcomeValue);
    outcome.addOutcomeOdd(buildOutcomeOdd(value));
    return outcome;
  }
}
