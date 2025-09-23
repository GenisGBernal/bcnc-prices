/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.application.ports.driving;

import com.bcnc.prices.application.config.impl.FindActivePriceCache;
import com.bcnc.prices.domain.filters.ActivePriceFilter;
import com.bcnc.prices.domain.models.values.ActivePrice;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PriceUseCasePort {

  Page<ActivePrice> find(ActivePriceFilter filter, Pageable pageable);
}
