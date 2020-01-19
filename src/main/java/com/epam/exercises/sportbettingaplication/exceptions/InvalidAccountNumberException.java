package com.epam.exercises.sportbettingaplication.exceptions;

public class InvalidAccountNumberException extends RuntimeException {

  public InvalidAccountNumberException(String message) {
    super(message);
  }
}

