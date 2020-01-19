package com.epam.exercises.sportbettingaplication;

import com.epam.exercises.sportbettingaplication.domain.user.Player;
import com.epam.exercises.sportbettingaplication.enums.Currency;
import com.epam.exercises.sportbettingaplication.service.DataPreparationService;
import com.epam.exercises.sportbettingaplication.service.EventLoger;
import com.epam.exercises.sportbettingaplication.service.impl.ConsoleEventLogger;
import com.epam.exercises.sportbettingaplication.service.impl.DataPreparationServiceImpl;
import com.epam.exercises.sportbettingaplication.ui.OutputService;
import com.epam.exercises.sportbettingaplication.ui.impl.OutputServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;

public class App {

  private static EventLoger eventLoger = new ConsoleEventLogger();
  private static DataPreparationService dataPreparationService = new DataPreparationServiceImpl();
  private static OutputService outputService = new OutputServiceImpl();

  /**
   * main method of the sportbetting application.
   *
   * @param args provided arguments.
   */

  public static void main(String[] args) {

    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

      String name = outputService.getPlayerName(bufferedReader);

      String accountNumber = outputService.getAccountNumber(bufferedReader);

      String balanceInput = outputService.getBalanceInput(bufferedReader);

      String currencyInput = outputService.getCurrencyInput(bufferedReader);
      Currency currency = Currency.valueOf(currencyInput);

      String birthInput = outputService.getBirthInput(bufferedReader);
      LocalDate birth = LocalDate.parse(birthInput);

      BigDecimal balance = new BigDecimal(balanceInput);
      Player player = dataPreparationService
                .createPlayer(name, accountNumber, balance, currency, birth);

      eventLoger.logEvent(
                "Welcome " + player.getName() + "!\n" + "Your balance is "
                          + player.getBalance() + " " + player.getCurrency());
      outputService.makeBet(bufferedReader, player);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
