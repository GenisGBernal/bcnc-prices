/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcnc.prices.domain.filters.active_price.ActivePriceFilter;
import com.bcnc.prices.domain.models.values.ActivePrice;
import com.bcnc.prices.repository.filters.ActivePriceSpecification;
import com.bcnc.prices.repository.mappers.PriceRepositoryMapper;
import com.bcnc.prices.repository.models.PriceMO;
import com.bcnc.prices.repository.repositories.PriceRepository;
import java.util.function.Function;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class PriceRepositoryAdapterTest {

  @InjectMocks private PriceRepositoryAdapter adapter;
  @Mock private PriceRepository priceRepository;
  @Mock private PriceRepositoryMapper priceRepositoryMapper;

  @Nested
  class FindActivePrice {

    @Test
    void shouldForwardOptional_whenRepositoryReturnsValue() {
      // given
      ActivePriceFilter filter = mock(ActivePriceFilter.class);
      Pageable pageable = mock(Pageable.class);

      Page<PriceMO> findAllResult = mock(Page.class);
      when(priceRepository.findAll(any(ActivePriceSpecification.class), eq(pageable)))
          .thenReturn(findAllResult);

      Page<ActivePrice> expected = mock(Page.class);
      when(findAllResult.map(any(Function.class))).thenReturn(expected);

      // when
      Page<ActivePrice> result = adapter.find(filter, pageable);

      // then
      assertEquals(expected, result);
    }
  }
}
