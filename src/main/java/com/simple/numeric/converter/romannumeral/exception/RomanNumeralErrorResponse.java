package com.simple.numeric.converter.romannumeral.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
/** This represents a error response object to be sent over in case of errors **/
public class RomanNumeralErrorResponse {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    /**
     * Default Constructor
     */
    public RomanNumeralErrorResponse() {
        super();
    }


    /**
     * Overloaded Constructor
     * @param status
     * @param message
     * @param errors
     */
    public RomanNumeralErrorResponse(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    /**
     * Overloaded Constructor
     * @param status
     * @param message
     * @param error
     */
    public RomanNumeralErrorResponse(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    /**
     * Getter for status
     * @return HttpStatus
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Setter for status
     * @param status
     */
    public void setStatus(final HttpStatus status) {
        this.status = status;
    }

    /**
     * Getter for message
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for message
     * @param message
     */
    public void setMessage(final String message) {
        this.message = message;
    }


    /**
     * Getter for errors
     * @return List of Error Strings
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Setter for Errors
     * @param errors
     */
    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    /**
     * Setter for error
     * @param error
     */
    public void setError(final String error) {
        errors = Arrays.asList(error);
    }

}