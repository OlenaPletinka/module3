package com.epam.exercises.sportbettingaplication.ui;

import com.epam.exercises.sportbettingaplication.domain.OutputBetObject;
import com.epam.exercises.sportbettingaplication.domain.outcome.Outcome;
import com.epam.exercises.sportbettingaplication.domain.user.Player;
import com.epam.exercises.sportbettingaplication.domain.wager.Wager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface OutputService {

  List<OutputBetObject> getBetsOutput();

  void getResultOutput(Outcome winOutcome, Wager wager);

  void makeBet(BufferedReader bufferedReader, Player player) throws IOException;

  String getPlayerName(BufferedReader bufferedReader) throws IOException;

  String getAccountNumber(BufferedReader bufferedReader) throws IOException;

  String getBalanceInput(BufferedReader bufferedReader) throws IOException;

  String getCurrencyInput(BufferedReader bufferedReader) throws IOException;

  String getBirthInput(BufferedReader bufferedReader) throws IOException;
}
