package com.epam.exercises.sportbettingaplication.domain.wager;

import com.epam.exercises.sportbettingaplication.domain.outcome.OutcomeOdd;
import com.epam.exercises.sportbettingaplication.domain.user.Player;
import com.epam.exercises.sportbettingaplication.enums.Currency;

import java.math.BigDecimal;

public class WagerBuilder {

  private OutcomeOdd outcomeOdd;
  private Player player;
  private BigDecimal amount;
  private Currency currency;
  private Integer timestamp;
  private boolean isProcessed;
  private boolean isWin;

  public WagerBuilder setOutcomeOdd(OutcomeOdd outcomeOdd) {
    this.outcomeOdd = outcomeOdd;
    return this;
  }

  public WagerBuilder setPlayer(Player player) {
    this.player = player;
    return this;
  }

  public WagerBuilder setAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  public WagerBuilder setCurrency(Currency currency) {
    this.currency = currency;
    return this;
  }

  public WagerBuilder setTimestamp(Integer timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public WagerBuilder setIsProcessed(boolean isProcessed) {
    this.isProcessed = isProcessed;
    return this;
  }

  public WagerBuilder setIsWin(boolean isWin) {
    this.isWin = isWin;
    return this;
  }

  public Wager createWager() {
    return new Wager(outcomeOdd, player, amount, currency, timestamp, isProcessed, isWin);
  }
}