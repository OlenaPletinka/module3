package com.epam.exercises.sportbettingaplication.domain.user;

import com.epam.exercises.sportbettingaplication.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlayerBuilder {

  private String name;
  private String accountNumber;
  private BigDecimal balance;
  private Currency currency;
  private LocalDate birth;

  public PlayerBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public PlayerBuilder setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  public PlayerBuilder setBalance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  public PlayerBuilder setCurrency(Currency currency) {
    this.currency = currency;
    return this;
  }

  public PlayerBuilder setBirth(LocalDate birth) {
    this.birth = birth;
    return this;
  }

  public Player createPlayer() {
    return new Player(name, accountNumber, balance, currency, birth);
  }
}