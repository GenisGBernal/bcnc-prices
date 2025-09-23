/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.application.services;

import com.bcnc.prices.application.ports.driven.PriceRepositoryPort;
import com.bcnc.prices.domain.filters.PaginationRequest;
import com.bcnc.prices.domain.filters.active_price.ActivePriceFilter;
import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import com.bcnc.prices.domain.models.values.ActivePrice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService {

  private final PriceRepositoryPort repositoryPort;

  public Page<ActivePrice> find(
      ActivePriceFilter filter, PaginationRequest<ActivePriceSortFieldEnum> pageable) {
    return repositoryPort.find(filter, pageable);
  }
}
