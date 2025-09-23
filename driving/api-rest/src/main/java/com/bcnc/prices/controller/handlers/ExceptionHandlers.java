/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.controller.handlers;

import com.bcnc.prices.api.rest.dto.ErrorResponseDTO;
import com.bcnc.prices.application.exceptions.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

  public static final String BAD_REQUEST_TITLE = "Bad Request";

  @ExceptionHandler(value = BadRequestException.class)
  public ResponseEntity<Object> handeException(BadRequestException ex, HttpServletRequest request) {
    ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();

    errorResponseDTO.status(HttpStatus.BAD_REQUEST.value());
    errorResponseDTO.title(BAD_REQUEST_TITLE);
    errorResponseDTO.instance(request.getRequestURI());
    errorResponseDTO.detail(ex.getLocalizedMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
  }
}
