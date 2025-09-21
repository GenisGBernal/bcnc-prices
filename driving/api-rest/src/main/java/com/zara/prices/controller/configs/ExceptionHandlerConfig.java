package com.zara.prices.controller.configs;

import com.zara.prices.application.exceptions.BadRequestException;
import com.zara.prices.application.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleConflictException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> handleConflictException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}

