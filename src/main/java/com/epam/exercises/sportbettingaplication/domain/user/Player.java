package com.epam.exercises.sportbettingaplication.domain.user;

import com.epam.exercises.sportbettingaplication.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class Player extends User {

  private String name;
  private String accountNumber;
  private BigDecimal balance;
  private Currency currency;
  private LocalDate birth;

  /**
   * constructor for the player.
   * @param name given argument.
   * @param accountNumber given argument.
   * @param balance given argument.
   * @param currency given argument.
   * @param birth given argument.
   */
  public Player(String name, String accountNumber, BigDecimal balance, Currency currency,
                LocalDate birth) {
    this.name = name;
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.currency = currency;
    this.birth = birth;
  }

  @Override
  public String toString() {
    return "Player{" + "name='" + name + '\'' + ", accountNumber='" + accountNumber + '\''
      + ", balance=" + balance + ", currency=" + currency + ", birth=" + birth + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return Objects.equals(name, player.name) &&
              Objects.equals(accountNumber, player.accountNumber) &&
              Objects.equals(balance, player.balance) &&
              currency == player.currency &&
              Objects.equals(birth, player.birth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, accountNumber, balance, currency, birth);
  }
}
