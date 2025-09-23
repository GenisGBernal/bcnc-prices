package com.bcnc.prices.repository.utils;

import com.bcnc.prices.controller.utils.ValidatorUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ValidatorUtilsTest {

  @Test
  void shouldReturnTrueWhenDateMatchesPattern() {
    String validDate = "2023-09-23T15:30:45";

    boolean result = ValidatorUtils.validateString(validDate);

    assertTrue(result);
  }

  @Test
  void shouldReturnFalseWhenDateDoesNotMatchPattern() {
    String invalidDate = "23-09-2023";

    boolean result = ValidatorUtils.validateString(invalidDate);

    assertFalse(result);
  }
}
