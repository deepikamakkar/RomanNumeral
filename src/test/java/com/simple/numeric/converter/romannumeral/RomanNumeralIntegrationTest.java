package com.simple.numeric.converter.romannumeral;

import com.simple.numeric.converter.romannumeral.exception.RomanNumeralErrorResponse;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration Test for Controller and Exception Handler
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RomanNumeralIntegrationTest {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    /**
     * This test checks for default value if nothing is passed to params
     * @throws URISyntaxException
     * @throws JSONException
     */
    @Test
    public void defaultValue() throws URISyntaxException, JSONException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/romannumeral?query=";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        String expectedString = "{number:1, romanNumeral:I}";
        JSONAssert.assertEquals(expectedString, response.getBody(), false);
    }

    /**
     * This test validates the correct output produced for valid input
     * @throws URISyntaxException
     * @throws JSONException
     */
    @Test
    public void getRomanNumeral() throws URISyntaxException, JSONException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/romannumeral?query=10";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        String expectedString = "{number:10, romanNumeral:X}";
        JSONAssert.assertEquals(expectedString, response.getBody(), false);
    }

    /**
     * This test validates if out of valid range input is passed i.e Min =1 and Max =3999
     * @throws URISyntaxException
     * @throws JSONException
     */
    @Test
    public void whenConstraintViolation() throws URISyntaxException{
        final String baseUrl = "http://localhost:" + randomServerPort + "/romannumeral?query=4000";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<RomanNumeralErrorResponse> response = testRestTemplate.exchange(uri, HttpMethod.GET, request, RomanNumeralErrorResponse.class);
        assertEquals(1, response.getBody().getErrors().size());
        assertTrue(response.getBody().getErrors().get(0).contains("Input out of range"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getStatus());
    }

    /**
     * This test validated for invalid argument type send to request
     * @throws URISyntaxException
     */
    @Test
    public void whenMethodArgumentTypeMismatchException() throws URISyntaxException{
        final String baseUrl = "http://localhost:" + randomServerPort + "/romannumeral?query=" +
                "..";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<RomanNumeralErrorResponse> response = testRestTemplate.exchange(uri, HttpMethod.GET, request, RomanNumeralErrorResponse.class);
        assertEquals(1, response.getBody().getErrors().size());
        assertTrue(response.getBody().getErrors().get(0).contains("should be of type"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getStatus());
    }

}
