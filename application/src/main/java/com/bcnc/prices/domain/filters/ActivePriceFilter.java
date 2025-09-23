package com.bcnc.prices.domain.filters;

import java.time.LocalDateTime;

public record ActivePriceFilter(
  LocalDateTime date,
  Long productId,
  Long brandId
) {}
