/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Price {

  private Long brandId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Long priceListId;
  private Long productId;
  private Integer priority;
  private BigDecimal price;
  private String currency;
}
