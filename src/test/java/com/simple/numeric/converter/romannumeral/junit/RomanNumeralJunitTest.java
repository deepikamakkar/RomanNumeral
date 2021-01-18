package com.simple.numeric.converter.romannumeral.junit;

import com.simple.numeric.converter.romannumeral.model.RomanNumeral;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RomanNumeralJunitTest {
   //Valid Input Test
    @ParameterizedTest
    @CsvSource({"5,V", "10,X", "3999,MMMCMXCIX", "2000,MM"})
    public void validInputRomanNumeral(int input, String expected){
        RomanNumeral romanNumeral = new RomanNumeral(input);
        assertEquals(expected, romanNumeral.getRomanNumeral());
    }

    //Invalid Input Test
    @ParameterizedTest
    @CsvSource({"0", "-1", "-98", "-6000"})
    public void invalidInputRomanNumeral(int input){
        RomanNumeral romanNumeral = new RomanNumeral(input);
        assertEquals("", romanNumeral.getRomanNumeral());
    }
}
