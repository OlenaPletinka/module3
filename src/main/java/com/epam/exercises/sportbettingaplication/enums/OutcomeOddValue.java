package com.epam.exercises.sportbettingaplication.enums;

public enum OutcomeOddValue {
  OUTCOME_ODD_VALUE_10_0(10.0),
  OUTCOME_ODD_VALUE_4_0(4.0),
  OUTCOME_ODD_VALUE_1_3(1.3);

  private Double value;

  OutcomeOddValue(Double value) {
    this.value = value;
  }

  public Double getValue() {
    return value;
  }
}
