package com.simple.numeric.converter.romannumeral.controller;

import com.simple.numeric.converter.romannumeral.model.RomanNumeral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RomanNumeralController {
    // Logger for this class
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * This method  return RomanNumeral for the number,
     * if its in valid range otherwise throws an exception
     * @param query input number
     * @return
     */
    @GetMapping("/romannumeral")
    public RomanNumeral romanNumeral(@RequestParam(value="query", defaultValue = "1") Integer query){
        LOGGER.info("Input paramter :" +query);
        if(query<1 || query > 3999){
            LOGGER.debug("Out of range input param:" +query);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Number is not in defined range: 1-3999");
        }
         return new RomanNumeral(query);
    }

}
