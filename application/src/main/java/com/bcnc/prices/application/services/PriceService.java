/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.application.services;

import com.bcnc.prices.application.config.impl.FindActivePriceCache;
import com.bcnc.prices.application.ports.driven.PriceRepositoryPort;
import com.bcnc.prices.domain.models.values.ActivePrice;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService {

  private final PriceRepositoryPort repositoryPort;

  // Query params include a date, cache hits will probably be low
  @Cacheable(cacheNames = FindActivePriceCache.NAME)
  public Optional<ActivePrice> findActivePrice(LocalDateTime date, Long productId, Long brandId) {
    return repositoryPort.findActivePrice(date, productId, brandId);
  }
}
