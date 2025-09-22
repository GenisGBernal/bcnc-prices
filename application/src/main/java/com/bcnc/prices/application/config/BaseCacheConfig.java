/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.application.config;

public interface BaseCacheConfig {
  String getName();

  Long getExpireAfterWriteSeconds();

  Long getExpireAfterAccessSeconds();

  Long getRefreshAfterWrite();

  int getInitialSize();

  long getMaximumSize();
}
