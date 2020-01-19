package com.epam.exercises.sportbettingaplication.service.impl;

import com.epam.exercises.sportbettingaplication.service.EventLoger;

public class ConsoleEventLogger implements EventLoger {

  @Override
  public void logEvent(String massage) {
    System.out.println(massage);
  }
}
