/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.domain.filters;

import org.springframework.data.domain.Sort;

public record PaginationRequest<S>(
    Integer pageNumber, Integer pageSize, Sort.Direction sortDir, S sortField) {

  public static <S> PaginationRequest<S> of(
      Integer pageNumber, Integer pageSize, Sort.Direction sortDir, S sortField) {
    return new PaginationRequest<>(pageNumber, pageSize, sortDir, sortField);
  }
}
