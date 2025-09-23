/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.application.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public class BusinessException extends RuntimeException {
  public BusinessException() {
    super();
  }

  public BusinessException(String message) {
    super(message);
  }

  @Override
  public String getLocalizedMessage() {
    try {
      Locale locale = LocaleContextHolder.getLocale();
      ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
      return bundle.getString(getMessage());
    } catch (Exception e) {
      log.error("Error localizing message", e);
      return getMessage();
    }
  }
}
