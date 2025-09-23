/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcnc.prices.application.ports.driven.PriceRepositoryPort;
import com.bcnc.prices.application.services.PriceService;
import com.bcnc.prices.domain.filters.active_price.ActivePriceFilter;
import com.bcnc.prices.domain.models.values.ActivePrice;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

  @InjectMocks private PriceService service;
  @Mock private PriceRepositoryPort priceRepositoryPort;

  @Nested
  class FindActivePrices {

    @Test
    void shouldForwardOptional_whenRepositoryPortReturnsValue() {
      // given
      ActivePriceFilter filter = mock(ActivePriceFilter.class);
      Pageable pageable = mock(Pageable.class);

      Page<ActivePrice> expected = mock(Page.class);
      when(priceRepositoryPort.find(filter, pageable)).thenReturn(expected);

      // when
      Page<ActivePrice> result = service.find(filter, pageable);

      // then
      assertEquals(expected, result);
    }
  }
}
