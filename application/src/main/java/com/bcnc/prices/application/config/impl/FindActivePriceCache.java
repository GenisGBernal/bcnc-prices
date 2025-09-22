/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.application.config.impl;

import com.bcnc.prices.application.config.BaseCacheConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindActivePriceCache implements BaseCacheConfig {

  public static final String NAME = "FIND_ACTIVE_PRICE_CACHE";

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public Long getExpireAfterWriteSeconds() {
    return 60L; // 1 Minute
  }

  @Override
  public Long getExpireAfterAccessSeconds() {
    return null;
  }

  @Override
  public Long getRefreshAfterWrite() {
    return null;
  }

  @Override
  public int getInitialSize() {
    return 100;
  }

  @Override
  public long getMaximumSize() {
    return 5000;
  }
}
