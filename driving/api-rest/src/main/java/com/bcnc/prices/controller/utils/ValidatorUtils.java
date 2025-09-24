/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.controller.utils;

import com.bcnc.prices.application.exceptions.BadRequestException;
import com.bcnc.prices.controller.configs.DateTimeConfig;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidatorUtils {

  public boolean validateString(String date) throws BadRequestException {
    return date != null && date.matches(DateTimeConfig.LOCAL_DATE_TIME_PATTERN_REGEX);
  }
}
