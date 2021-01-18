package com.simple.numeric.converter.romannumeral;

import com.simple.numeric.converter.romannumeral.controller.RomanNumeralController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Validation for context creating controller
 */
@SpringBootTest
public class SmokeTest {

    @Autowired
    RomanNumeralController numeralController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(numeralController).isNotNull();
    }
}
