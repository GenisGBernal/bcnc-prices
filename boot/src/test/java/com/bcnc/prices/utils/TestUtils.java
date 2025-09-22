/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {

  public static String readJson(String classpathResource) {
    try (InputStream is = TestUtils.class.getResourceAsStream(classpathResource)) {
      if (is == null) {
        throw new RuntimeException("Resource not found: " + classpathResource);
      }
      try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
        return scanner.useDelimiter("\\A").next();
      }
    } catch (Exception e) {
      throw new RuntimeException("Failed to read JSON resource: " + classpathResource, e);
    }
  }
}
