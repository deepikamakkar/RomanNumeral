package com.simple.numeric.converter.romannumeral.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RomanNumeralTest {
    /**
     * Valid inputs test
     *
     * @param input
     * @param expected
     */
    @ParameterizedTest
    @CsvSource({"5,V", "10,X", "3999,MMMCMXCIX", "2000,MM"})
    public void validInputRomanNumeral(int input, String expected) {
        RomanNumeral romanNumeral = new RomanNumeral(input);
        assertEquals(expected, romanNumeral.getRomanNumeral());
    }


    /**
     * Invalid input test
     *
     * @param input
     */
    @ParameterizedTest
    @CsvSource({"0", "-1", "-98", "-6000"})
    public void invalidInputRomanNumeral(int input) {
        RomanNumeral romanNumeral = new RomanNumeral(input);
        assertEquals("", romanNumeral.getRomanNumeral());
    }

    /**
     * This is to make test coverage 100% for RomanNumeral class
     */
    @Test
    public void toStringTest(){
        RomanNumeral romanNumeral = new RomanNumeral(10);
        String expectedString= "RomanNumeral{number=10, romanNumeral='X'}";
        assertEquals(expectedString, romanNumeral.toString());
    }
}
