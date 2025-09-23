/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.application.exceptions;

public class BadRequestException extends BusinessException {


  public BadRequestException() {
    super();
  }

  public BadRequestException(String message) {
    super(message);
  }
}
