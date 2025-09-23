/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.controller.configs;

import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateTimeConfig {

  public static final String LOCAL_DATE_TIME_PATTERN_REGEX = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$";
  public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

  public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN);
}
