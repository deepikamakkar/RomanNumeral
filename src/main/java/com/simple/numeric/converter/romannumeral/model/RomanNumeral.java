package com.simple.numeric.converter.romannumeral.model;

import java.util.TreeMap;

public class RomanNumeral {
    private final static TreeMap<Integer,String> numberRomanMap = new TreeMap<>();

    private final Integer number;
    private final String romanNumeral;

    /**
     * Creating static block to load mapping, once class is loaded
     * We are using TreeMap as it maintains order to natural ordering of keys
     * which will be leveraged to obtain the greatest key less than or equal to number passed in
     */
    static{
        numberRomanMap.put(1,"I");
        numberRomanMap.put(4,"IV");
        numberRomanMap.put(5,"V");
        numberRomanMap.put(9,"IX");
        numberRomanMap.put(10,"X");
        numberRomanMap.put(40,"XL");
        numberRomanMap.put(50,"L");
        numberRomanMap.put(90,"XC");
        numberRomanMap.put(100,"C");
        numberRomanMap.put(400,"CD");
        numberRomanMap.put(500,"D");
        numberRomanMap.put(900,"CM");
        numberRomanMap.put(1000,"M");
    }


    /**
     * Constructor
     * @param number
     */
    public RomanNumeral(int number){
        this.number = number;
        this.romanNumeral = toRoman(number);
    }

    /**
     *
     * @param number input for conversion to roman numeral
     * @return the roman numeral for the input
     */
    private static final String toRoman(int number){
        StringBuilder romanValue = new StringBuilder();
        while(number>0 && number <4000){
            int floorNumber = numberRomanMap.floorKey(number);
            String romanNumeral = numberRomanMap.get(floorNumber);
            romanValue.append(romanNumeral);
            number = number-floorNumber;
        }
        return romanValue.toString();
    }

    /**
     * Getter for Number
     * @return Integer
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Getter for RomanNumeral
     * @return String
     */
    public String getRomanNumeral() {
        return romanNumeral;
    }

    /**
     * Produces String as defined
     * @return String
     */
    @Override
    public String toString() {
        return "RomanNumeral{" +
                "number=" + number +
                ", romanNumeral='" + romanNumeral + '\'' +
                '}';
    }
}
