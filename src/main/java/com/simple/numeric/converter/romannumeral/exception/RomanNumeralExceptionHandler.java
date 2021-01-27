package com.simple.numeric.converter.romannumeral.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;


/**
 * This class is used handle global exceptions
 */
@RestControllerAdvice
public class RomanNumeralExceptionHandler {

    /**
     * This method handles MethodArgumentTypeMismatchException for cases like a string passed to expected integer type
     *
     * @param e
     * @return Response Object RomanNumeralErrorResponse
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RomanNumeralErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        String error =
                e.getName() + " should be of type " + e.getRequiredType().getName();
        RomanNumeralErrorResponse romanNumeralErrorResponse =
                new RomanNumeralErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), error);
        return new ResponseEntity<>(
                romanNumeralErrorResponse, new HttpHeaders(), romanNumeralErrorResponse.getStatus());
    }

    /**
     * This method handles ConstraintViolationException i.e. if the service received the request params exceeding particular value defined.
     * @param e
     * @return Response Object RomanNumeralErrorResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<RomanNumeralErrorResponse> handleConstraintViolationException(ConstraintViolationException e){
        String error = "Input out of range";
        RomanNumeralErrorResponse romanNumeralErrorResponse =
                new RomanNumeralErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), error);
        return new ResponseEntity<>(
                romanNumeralErrorResponse, new HttpHeaders(), romanNumeralErrorResponse.getStatus());
    }

    /**
     * This is a default catch all for exceptions not caught by above defined handlers
     *
     * @param e
     * @return Response Object RomanNumeralErrorResponse
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RomanNumeralErrorResponse> handleAll(Exception e){
        RomanNumeralErrorResponse romanNumeralErrorResponse = new RomanNumeralErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "Caught an Error");
        return new ResponseEntity<>(romanNumeralErrorResponse, new HttpHeaders(), romanNumeralErrorResponse.getStatus());
    }

}
