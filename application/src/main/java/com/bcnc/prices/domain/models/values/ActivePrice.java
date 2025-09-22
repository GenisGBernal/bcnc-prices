/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.domain.models.values;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ActivePrice(
    Long brandId,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Long priceListId,
    Long productId,
    BigDecimal price,
    String currency) {}
