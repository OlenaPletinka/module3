package com.epam.exercises.sportbettingaplication.ui.impl;

import com.epam.exercises.sportbettingaplication.domain.OutputBetObject;
import com.epam.exercises.sportbettingaplication.domain.outcome.Outcome;
import com.epam.exercises.sportbettingaplication.domain.sportevent.Result;
import com.epam.exercises.sportbettingaplication.domain.user.Player;
import com.epam.exercises.sportbettingaplication.domain.wager.Wager;
import com.epam.exercises.sportbettingaplication.domain.wager.WagerBuilder;
import com.epam.exercises.sportbettingaplication.enums.Currency;
import com.epam.exercises.sportbettingaplication.service.DataProcessorService;
import com.epam.exercises.sportbettingaplication.service.EventLoger;
import com.epam.exercises.sportbettingaplication.service.impl.ConsoleEventLogger;
import com.epam.exercises.sportbettingaplication.service.impl.DataProcessorServiceImpl;
import com.epam.exercises.sportbettingaplication.ui.OutputService;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OutputServiceImpl implements OutputService {

  private static List<Wager> wagers = new ArrayList<>();
  private InputValidatorImpl inputValidator = new InputValidatorImpl();

  private DataProcessorService dataProcessorService = new DataProcessorServiceImpl();
  private EventLoger eventLoger = new ConsoleEventLogger();

  @Override
  public List<OutputBetObject> getBetsOutput() {
    List<OutputBetObject> outputBetObjects = dataProcessorService.createOutputBetObjects();
    eventLoger.logEvent("Please choose an outcome to bet on! (choose a number or press q for quit)");
    for (int i = 1; i <= outputBetObjects.size(); i++) {
      OutputBetObject outputBetObject = outputBetObjects.get(i - 1);
      eventLoger.logEvent(String.format(
                "%d: Bet on the %s sport event, %s. The odd on this is %f, valid from %s to %s", i,
                outputBetObject.getSportEventTitle(), outputBetObject.getOutcomeValue(),
                outputBetObject.getOutcomeOddValue(), outputBetObject.getFromDate(),
                outputBetObject.getToDate()));
    }
    return outputBetObjects;
  }

  @Override
  public void getResultOutput(Outcome winOutcome, Wager wager) {
    eventLoger.logEvent("Results:");
    eventLoger.logEvent(String
              .format("The winner is Outcome %d [outcomeOdds=%f and valid from %s to %s]",
                        wager.getTimestamp(), wager.getOutcomeOdd().getValue(),
                        wager.getOutcomeOdd().getFromDate(), wager.getOutcomeOdd().getToDate()));
    eventLoger.logEvent(String.format("You have won %f %s",
              wager.getAmount().multiply(BigDecimal.valueOf(wager.getOutcomeOdd().getValue())),
              wager.getCurrency()));

  }

  @Override
  public void makeBet(BufferedReader bufferedReader, Player player) {
    List<OutputBetObject> betsOutput = getBetsOutput();

    try {
     String firstBetOn = bufferedReader.readLine();

      inputValidator.validateFirstBetOn(firstBetOn);

      if (firstBetOn.equals("q")) {
        getResults(wagers);
      } else {
        int choice = Integer.parseInt(firstBetOn);
        eventLoger.logEvent("How much do you want to bet on it? (q for quit)");
        BigDecimal firstBet = BigDecimal.valueOf(Long.parseLong(bufferedReader.readLine()));
        discardPlayer(player, firstBet);
        wagers.add(createWagger(player, firstBet, betsOutput.get(choice - 1), choice));
        makeBet(bufferedReader, player);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getPlayerName(BufferedReader bufferedReader) throws IOException {
    eventLoger.logEvent("Hi, what is your name?");
    String name = bufferedReader.readLine();
    inputValidator.validateName(name);
    return name;
  }

  @Override
  public String getAccountNumber(BufferedReader bufferedReader) throws IOException {
    eventLoger.logEvent("What is your account number?");
    String accountNumber = bufferedReader.readLine();
    inputValidator.validateAccountNumber(accountNumber);
    return accountNumber;
  }

  @Override
  public String getBalanceInput(BufferedReader bufferedReader) throws IOException {
    eventLoger.logEvent("How much money do you have (more than 0)?");
    String balanceInput = bufferedReader.readLine();
    inputValidator.validateBalanceInput(balanceInput);
    return balanceInput;
  }

  @Override
  public String getCurrencyInput(BufferedReader bufferedReader) throws IOException {
    eventLoger.logEvent("What is your currency? (UAH, EUR or USD)");
    String currencyInput = bufferedReader.readLine();
    inputValidator.validateCurrencyInput(currencyInput);
    return currencyInput;
  }

  @Override
  public String getBirthInput(BufferedReader bufferedReader) throws IOException {
    eventLoger.logEvent("When were you born? eg.:1990-02-03");
    String birthInput = bufferedReader.readLine();
    inputValidator.validateBirthInput(birthInput);
    return birthInput;
  }

  private void getResults(List<Wager> wagers) {
    Random rand = new Random();
    Wager winWager = wagers.get(rand.nextInt(wagers.size()));
    wagers.forEach(wager -> wager.setProcessed(true));
    winWager.setWin(true);

    Result result = new Result();
    Outcome winOutcome = winWager.getOutcomeOdd().getOutcome();
    result.addOutcomes(winOutcome);

    getResultOutput(winOutcome, winWager);
  }

  private Wager createWagger(Player player, BigDecimal firstBet, OutputBetObject outputBetObject,
                             Integer choice) {
    WagerBuilder wagerBuilder = new WagerBuilder();
    return wagerBuilder.setPlayer(player).setAmount(firstBet).setCurrency(player.getCurrency())
              .setTimestamp(choice).setOutcomeOdd(outputBetObject.getOutcomeOdd()).createWager();
  }

  private void discardPlayer(Player player, BigDecimal bet) {
    BigDecimal balance = player.getBalance();
    Currency currency = player.getCurrency();
    if (!inputValidator.isValidBet(balance, bet)) {
      eventLoger.logEvent("You don't have enough money, your balance is " + balance
                + " " + currency);
    } else {
      BigDecimal currentBalance = balance.subtract(bet);
      player.setBalance(currentBalance);
      eventLoger.logEvent("Your new balance is " + currentBalance + " " + currency);
    }
  }
}
