/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.adapters;

import com.bcnc.prices.application.ports.driven.PriceRepositoryPort;
import com.bcnc.prices.domain.models.values.ActivePrice;
import com.bcnc.prices.repository.repositories.PriceRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

  private final PriceRepository repository;

  @Override
  public Optional<ActivePrice> findActivePrice(LocalDateTime date, Long productId, Long brandId) {
    return repository.findActivePrice(date, productId, brandId);
  }
}
