/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.application.ports.driven;

import com.bcnc.prices.domain.models.values.ActivePrice;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {

  Optional<ActivePrice> findActivePrice(LocalDateTime date, Long productId, Long brandId);
}
