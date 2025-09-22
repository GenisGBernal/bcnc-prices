/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcnc.prices.domain.models.values.ActivePrice;
import com.bcnc.prices.repository.repositories.PriceRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceRepositoryAdapterTest {

  @InjectMocks private PriceRepositoryAdapter adapter;
  @Mock private PriceRepository priceRepository;

  @Nested
  class FindActivePrice {

    @Test
    void shouldForwardOptional_whenRepositoryReturnsValue() {
      // given
      LocalDateTime date = LocalDateTime.now();
      Long productId = 3424L;
      Long brandId = 3L;

      Optional<ActivePrice> expected = mock(Optional.class);
      when(priceRepository.findActivePrice(date, productId, brandId)).thenReturn(expected);

      // when
      Optional<ActivePrice> result = adapter.findActivePrice(date, productId, brandId);

      // then
      assertEquals(expected, result);
    }
  }
}
