package com.example.quizspark.assets;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler for the QuizSpark application.
 * Provides centralized handling of common exceptions thrown by controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link IllegalArgumentException}s thrown by any controller within the application.
     * Maps these exceptions to a {@link HttpStatus#BAD_REQUEST} response.
     *
     * @param ex The caught {@link IllegalArgumentException}.
     * @return A {@link ResponseEntity} with the exception message and {@link HttpStatus#BAD_REQUEST} status.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all uncaught exceptions thrown by any controller within the application.
     * Maps these exceptions to a {@link HttpStatus#INTERNAL_SERVER_ERROR} response.
     *
     * @param ex The caught {@link Exception}.
     * @return A {@link ResponseEntity} with the exception message and {@link HttpStatus#INTERNAL_SERVER_ERROR} status.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
