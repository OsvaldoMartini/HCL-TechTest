package com.db.dataplatform.techtest.server.api;

import java.util.Date;
import javassist.NotFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ConstraintViolationException.class})
  protected ResponseEntity<Object> handleConflict(
      ConstraintViolationException ex, WebRequest request) {
    String bodyOfResponse = "Provided input parameters have not met validation requirements";
    return handleExceptionInternal(
        ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {IllegalArgumentException.class})
  protected ResponseEntity<Object> handleConflict(IllegalArgumentException ex, WebRequest request) {
    ErrorMessage message =
        new ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            ex.getMessage(),
            "Check the the correct param for Block Type Enum!");

    return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {NotFoundException.class})
  public ResponseEntity<Object> handleNoLevelFoundException(
      NotFoundException ex, WebRequest request) {
    ErrorMessage message =
        new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            "Check the the correct param for Block Type Enum!");

    return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {RestClientException.class})
  public ResponseEntity<Object> handleNoLevelFoundException(
      RestClientException ex, WebRequest request) {
    ErrorMessage message =
        new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            "Check the the correct param for Block Type Enum!");

    return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }
}
