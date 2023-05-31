package com.project.creditcardpayment.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex) {
        logger.info("SQLException Occurred:: URL=" + request.getRequestURL());
        return "database_error";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "NullPointerException occurred")
    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointerException() {
        logger.error("NullPointerException handler executed");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "IOException occurred")
    @ExceptionHandler(IOException.class)
    public void handleIOException() {
        logger.error("IOException handler executed");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "IllegalArgumentException occurred")
    @ExceptionHandler(IllegalArgumentException.class)
    public void IllegalArgumentException() {
        logger.error("IllegalArgumentException handler executed");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ParseException occurred")
    @ExceptionHandler(ParseException.class)
    public void ParseException() {
        logger.error("ParseException handler executed");
    }


}