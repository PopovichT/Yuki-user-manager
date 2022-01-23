package com.example.emailverification.controller;

import com.example.emailverification.dto.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
    private final ZoneId zoneId = ZoneId.of("Europe/Berlin");

    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<Object> handleException(Exception ex) {
        HttpStatus status = HttpStatus.OK;
        CustomError error = new CustomError(ZonedDateTime.ofInstant(Instant.now(), zoneId), ex.getMessage());
        return new ResponseEntity<>(error, status);
    }
}
