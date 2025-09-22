/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.controller.handlers;

import com.bcnc.prices.application.exceptions.BadRequestException;
import com.bcnc.prices.application.exceptions.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<Object> handeException(NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @ExceptionHandler(value = BadRequestException.class)
  public ResponseEntity<Object> handeException(BadRequestException ex) {
    return getBadRequestResponse(ex.getMessage());
  }

  @ExceptionHandler(value = ConstraintViolationException.class)
  public ResponseEntity<Object> handeException(ConstraintViolationException ex) {
    return getBadRequestResponse(ex.getMessage());
  }

  private static ResponseEntity<Object> getBadRequestResponse(String ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
  }
}
