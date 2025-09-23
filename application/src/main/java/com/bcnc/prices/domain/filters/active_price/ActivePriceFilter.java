/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.domain.filters.active_price;

import java.time.LocalDateTime;

public record ActivePriceFilter(LocalDateTime date, Long productId, Long brandId) {}
