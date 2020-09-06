package io.github.edgardobarriam.springgcpchallenge;


import io.github.edgardobarriam.springgcpchallenge.util.RUTUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RUTUtilTest {
  
  String inputRut = "19.540.817-3";
  String nonformattedRut = "195408173";
  
  String nonValidRut = "19.540.817-K";
  String tooLongRut = "1234567891011121314";
  String shortInvalidInput = "123";
  String garbageInput = "123456478sdfghjsdfghj";
  
  @Test
  void givenValidFormattedRut_shouldReturnRutValid() {
    assertTrue(RUTUtils.isValid(inputRut));
  }
  
  @Test
  void givenValidDV_RutValidationDVShouldBeEqual() {
    assertEquals("3", RUTUtils.calcDV(nonformattedRut));
  }
  
  @Test
  void givenInvalidDV_RutValidationDVShouldNotBeEqual() {
    assertNotEquals("K", RUTUtils.calcDV(nonformattedRut));
  }
  
  @Test
  void givenValidNonFormattedRut_shouldReturnRutValid() {
    assertTrue(RUTUtils.isValid(nonformattedRut));
  }
  
  @Test
  void givenInvalidRut_shouldReturnRutNotValid() {
    assertFalse(RUTUtils.isValid(nonValidRut));
  }
  
  @Test
  void givenTooLongRut_shouldReturnRutNotValid() {
    assertFalse(RUTUtils.isValid(tooLongRut));
  }
  
  @Test
  void givenFormattedRut_shouldRemoveFormat() {
      assertEquals(nonformattedRut, RUTUtils.cleanRut(inputRut));
  }
  
  @Test
  void givenGarbageInput_shouldReturnRutNotValid() {
    assertFalse(RUTUtils.isValid(garbageInput));
  }
  
  @Test
  void givenShortInvalidInput_shouldReturnRutNotValid() {
    assertFalse(RUTUtils.isValid(shortInvalidInput));
  }
}
