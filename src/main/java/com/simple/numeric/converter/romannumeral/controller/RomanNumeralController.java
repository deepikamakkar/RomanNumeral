package com.simple.numeric.converter.romannumeral.controller;

import com.simple.numeric.converter.romannumeral.model.RomanNumeral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.*;

@RestController
@Validated
public class RomanNumeralController {
    /**
     * Logger for this class
     */
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * This method  return RomanNumeral for the number,
     * if its in valid range  of Min - 1 and Max - 3999
     * otherwise throws an ConstraintViolation exception
     * Also, assigns a default value of 1 if no argument is passed
     * @param query input number
     * @return
     */
    @GetMapping("/romannumeral")
    public RomanNumeral romanNumeral(@RequestParam(value="query", defaultValue = "1") @Min(1) @Max(3999) Integer query){
        LOGGER.info("Input paramter :" +query);
         return new RomanNumeral(query);
    }
}
