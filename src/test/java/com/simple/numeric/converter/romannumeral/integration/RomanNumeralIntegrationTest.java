package com.simple.numeric.converter.romannumeral.integration;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RomanNumeralIntegrationTest {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void defaultValue() throws URISyntaxException, JSONException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/romannumeral?query=";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        String expectedString = "{number:1, romanNumeral:I}";
        JSONAssert.assertEquals(expectedString, response.getBody(),false);
        }

    @Test
    public void getRomanNumeral() throws URISyntaxException, JSONException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/romannumeral?query=10";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        String expectedString = "{number:10, romanNumeral:X}";
        JSONAssert.assertEquals(expectedString, response.getBody(),false);
    }

    @Test
    public void outOfRangeValue() throws URISyntaxException, JSONException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/romannumeral?query=4000";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        assertThat(response.getStatusCodeValue(), is(400));
    }
    }
