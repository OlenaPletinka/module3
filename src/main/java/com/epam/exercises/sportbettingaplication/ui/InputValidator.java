package com.epam.exercises.sportbettingaplication.ui;

import java.math.BigDecimal;

public interface InputValidator {
  void validateName(String name);
  void validateAccountNumber(String accountNumber);
  void validateBalanceInput(String balanceInput);
  void validateCurrencyInput(String currencyInput);
  void validateBirthInput(String birthInput);
  void validateFirstBetOn(String firstBetOn);
  boolean isValidBet(BigDecimal balance, BigDecimal firstBet);
}
