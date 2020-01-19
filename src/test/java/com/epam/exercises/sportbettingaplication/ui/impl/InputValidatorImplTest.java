package com.epam.exercises.sportbettingaplication.ui.impl;

import com.epam.exercises.sportbettingaplication.exceptions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
@RunWith(MockitoJUnitRunner.class)
public class InputValidatorImplTest {

  @InjectMocks
  private InputValidatorImpl inputValidator;

  @Test(expected = InvalidNameInputException.class)
  public void validateNameWithExceptionTest() {
    //when
    inputValidator.validateName("123 lkj");
  }

  @Test
  public void validateNameTest(){
    //when
    inputValidator.validateName("Petryk Pyatochkyn");
  }

  @Test(expected = InvalidAccountNumberException.class)
  public void validateAccountNumberWithExceptionTest() {
    //when
    inputValidator.validateAccountNumber("account111");
  }
  @Test
  public void validateAccountNumberTest() {
    //when
    inputValidator.validateAccountNumber("123456");
  }

  @Test(expected = InvalidBalanceInputException.class)
  public void validateBalanceInputWithExceptionTest() {
    //when
    inputValidator.validateBalanceInput("1234.d");
  }

  @Test
  public void validateBalanceInputTest() {
    //when
    inputValidator.validateBalanceInput("1234.25");
  }

  @Test(expected = InvalidCurrencyInputException.class)
  public void validateCurrencyInputWithExcrptionTest() {
    //when
    inputValidator.validateCurrencyInput("XZB");
  }

  @Test
  public void validateCurrencyInputTest() {
    //when
    inputValidator.validateCurrencyInput("USD");
  }

  @Test(expected = InvalidBirthInputException.class)
  public void validateBirthInputWitExceptionTest() {
    //when
    inputValidator.validateBirthInput("12-12-2000");
  }

  @Test
  public void validateBirthInputTest() {
    //when
    inputValidator.validateBirthInput("1999-12-12");
  }

  @Test(expected = InvalidBetInputException.class)
  public void validateFirstBetOnWithExceptionTest() {
    //when
    inputValidator.validateFirstBetOn("2w");
  }

  @Test
  public void validateFirstBetOnTest() {
    //when
    inputValidator.validateFirstBetOn("q");
  }

  @Test
  public void isValidBetValidTest() {
    //when
    boolean actualResult = inputValidator.isValidBet(BigDecimal.valueOf(1234), BigDecimal.valueOf(12345));
    //then
    assertFalse(actualResult);
  }

  @Test
  public void isValidBetInValidTest() {
    //when
    boolean actualResult = inputValidator.isValidBet(BigDecimal.valueOf(123456), BigDecimal.valueOf(12345));
    //then
    assertTrue(actualResult);
  }
}