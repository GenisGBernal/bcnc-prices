/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.application.ports.driven;

import com.bcnc.prices.application.config.impl.FindActivePriceCache;
import com.bcnc.prices.domain.filters.PaginationRequest;
import com.bcnc.prices.domain.filters.active_price.ActivePriceFilter;
import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import com.bcnc.prices.domain.models.values.ActivePrice;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

public interface PriceRepositoryPort {

  // Query params include a date, cache hits will probably be low
  @Cacheable(cacheNames = FindActivePriceCache.NAME)
  Page<ActivePrice> find(
      ActivePriceFilter filter, PaginationRequest<ActivePriceSortFieldEnum> pageable);
}
