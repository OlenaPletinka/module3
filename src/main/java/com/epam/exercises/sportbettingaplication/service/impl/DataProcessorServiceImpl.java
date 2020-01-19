package com.epam.exercises.sportbettingaplication.service.impl;

import com.epam.exercises.sportbettingaplication.domain.OutputBetObject;
import com.epam.exercises.sportbettingaplication.domain.outcome.Outcome;
import com.epam.exercises.sportbettingaplication.domain.outcome.OutcomeOdd;
import com.epam.exercises.sportbettingaplication.domain.sportevent.FootballSportEvent;
import com.epam.exercises.sportbettingaplication.domain.sportevent.SportEvent;
import com.epam.exercises.sportbettingaplication.enums.BetType;
import com.epam.exercises.sportbettingaplication.service.DataPreparationService;
import com.epam.exercises.sportbettingaplication.service.DataProcessorService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.exercises.sportbettingaplication.enums.DataForBet.*;
import static com.epam.exercises.sportbettingaplication.enums.OutcomeOddValue.*;


public class DataProcessorServiceImpl implements DataProcessorService {

  private static final LocalDate FROM_DATE = LocalDate.of(2016, 2, 3);
  private  static final LocalDate TO_DATE = LocalDate.of(2016, 2, 5);

  private DataPreparationService dataPreparationService = new DataPreparationServiceImpl();

  @Override
  public List<OutputBetObject> createOutputBetObjects() {
    FootballSportEvent footballSportEvent = dataPreparationService
              .prepareFootballSportEvent(SPORT_EVENT.getValue());
    List<OutputBetObject> outputBetObjects = new ArrayList<>();
    outputBetObjects.add(
              createOutputBetObject(footballSportEvent, PLAYER.getValue(),
                        OUTCOME_ODD_VALUE_10_0.getValue(), BetType.PLAYERS_SCORE));
    outputBetObjects.add(
              createOutputBetObject(footballSportEvent, GOAL.getValue(), OUTCOME_ODD_VALUE_1_3.getValue(),
                        BetType.BETTING_FOR_GOALS));
    outputBetObjects.add(
              createOutputBetObject(footballSportEvent, WINNER.getValue(), OUTCOME_ODD_VALUE_4_0.getValue(),
                        BetType.WINNER));

    return outputBetObjects;
  }

  private OutputBetObject createOutputBetObject(SportEvent sportEvent, String outcomeValue,
                                               Double outcomeOddValue, BetType betType) {

    OutcomeOdd outcomeOdd = dataPreparationService
              .prepareOutcomeOdd(outcomeOddValue, FROM_DATE, TO_DATE);

    Outcome outcome = dataPreparationService.prepareOutcome(outcomeValue, outcomeOdd);

    outcomeOdd.setOutcome(outcome);

    dataPreparationService.prepareBet(sportEvent, betType, outcome);

    return new OutputBetObject(sportEvent.getTitle(), outcome, outcomeOdd, FROM_DATE, TO_DATE);
  }
}
