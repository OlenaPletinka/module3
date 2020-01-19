package com.epam.exercises.sportbettingaplication.ui.impl;

import com.epam.exercises.sportbettingaplication.enums.Currency;
import com.epam.exercises.sportbettingaplication.exceptions.*;
import com.epam.exercises.sportbettingaplication.ui.InputValidator;

import java.math.BigDecimal;

public class InputValidatorImpl implements InputValidator {

  /**
   * validate input for the name.
   * @param name given argument.
   * @throws InvalidNameInputException custom exception.
   */
  @Override
  public void validateName(String name){
    if (!name.matches("^[a-zA-Z_ ]*$")) {
      throw new InvalidNameInputException(
                "Please, enter valid name. Name can contain only letters!");
    }
  }

  /**
   * validate input for the account number.
   * @param accountNumber given argument.
   */
  @Override
  public void validateAccountNumber(String accountNumber) {
    if (!accountNumber.matches("^[0-9]*$")) {
      throw new InvalidAccountNumberException(
                "Please, enter valid account number. Account number can contain only numbers!");
    }
  }

  /**
   * validate input for the balance.
   * @param balanceInput given argument.
   */
  @Override
  public void validateBalanceInput(String balanceInput) {
    if (!balanceInput.matches("^[0-9]+([.][0-9]*)?|[.][0-9]+$")) {
      throw new InvalidBalanceInputException("Please, enter valid balance!");
    }
  }

  /**
   * validate input for the currency.
   * @param currencyInput given argument.
   */
  @Override
  public void validateCurrencyInput(String currencyInput) {
    if (!currencyInput.equals(Currency.EUR.getValue()) && !currencyInput
              .equals(Currency.UAH.getValue()) && !currencyInput.equals(Currency.USD.getValue())) {
      throw new InvalidCurrencyInputException(
                "Please, enter valid currency value"
                          + ". Possible values of the currency are: UAH, EUR or USD!");
    }
  }

  /**
   * validate input fot birth.
   * @param birthInput given argument.
   */
  @Override
  public void validateBirthInput(String birthInput) {
    if (!birthInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
      throw new InvalidBirthInputException(
                "Please, enter valid birth value. Correct format of the birth date is YYYY-MM-DD!");
    }
  }

  /**
   * validate input for the first bet.
   * @param firstBetOn given argument.
   */
  @Override
  public void validateFirstBetOn(String firstBetOn) {
    if (!firstBetOn.matches("^[0-9]*$") && !firstBetOn.equals("q")) {
      throw new InvalidBetInputException("Please, enter valid number or 'q' for quit");
    }
  }

  /**
   * validate bet.
   * @param balance given argument.
   * @param bet given argument.
   * @return isvalid.
   */
  @Override
  public boolean isValidBet(BigDecimal balance, BigDecimal bet) {
    return balance.compareTo(bet) >= 0;
  }
}
