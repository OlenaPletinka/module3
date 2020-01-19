package com.epam.exercises.sportbettingaplication.exceptions;

public class InvalidBetInputException extends RuntimeException {

  public InvalidBetInputException(String message) {
    super(message);
  }
}
