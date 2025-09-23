/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.adapters;

import com.bcnc.prices.application.ports.driven.PriceRepositoryPort;
import com.bcnc.prices.domain.filters.ActivePriceFilter;
import com.bcnc.prices.domain.models.values.ActivePrice;
import com.bcnc.prices.repository.mappers.PriceRepositoryMapper;
import com.bcnc.prices.repository.repositories.PriceRepository;

import com.bcnc.prices.repository.specifications.ActivePriceSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

  private final PriceRepository repository;
  private final PriceRepositoryMapper mapper;

  public Page<ActivePrice> find(ActivePriceFilter filter, Pageable pageable) {
    return repository.findAll(ActivePriceSpecification.of(filter), pageable)
        .map(mapper::toActivePrice);
  }
}
