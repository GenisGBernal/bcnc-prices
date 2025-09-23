/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.application.ports.driving;

import com.bcnc.prices.domain.filters.PaginationRequest;
import com.bcnc.prices.domain.filters.active_price.ActivePriceFilter;
import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import com.bcnc.prices.domain.models.values.ActivePrice;
import org.springframework.data.domain.Page;

public interface PriceUseCasePort {

  Page<ActivePrice> find(
      ActivePriceFilter filter, PaginationRequest<ActivePriceSortFieldEnum> pageable);
}
