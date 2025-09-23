/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.application.use_cases;

import com.bcnc.prices.application.ports.driving.PriceUseCasePort;
import com.bcnc.prices.application.services.PriceService;
import com.bcnc.prices.domain.filters.ActivePriceFilter;
import com.bcnc.prices.domain.models.values.ActivePrice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PriceUseCase implements PriceUseCasePort {

  private final PriceService service;

  @Transactional(readOnly = true)
  @Override
  public Page<ActivePrice> find(ActivePriceFilter filter, Pageable pageable) {
    return service.find(filter, pageable);
  }
}
