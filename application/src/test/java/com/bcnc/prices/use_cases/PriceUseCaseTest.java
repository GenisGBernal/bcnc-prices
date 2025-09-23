/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.use_cases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcnc.prices.application.exceptions.NotFoundException;
import com.bcnc.prices.application.services.PriceService;
import com.bcnc.prices.application.use_cases.PriceUseCase;
import com.bcnc.prices.domain.filters.ActivePriceFilter;
import com.bcnc.prices.domain.models.values.ActivePrice;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class PriceUseCaseTest {

  @InjectMocks private PriceUseCase useCase;
  @Mock private PriceService priceService;

  @Nested
  class FindActivePrice {

    @Test
    void shouldReturnActivePrice_whenServiceReturnsValue() {
      // given
      ActivePriceFilter filter = mock(ActivePriceFilter.class);
      Pageable pageable = mock(Pageable.class);

      Page<ActivePrice> expected = mock(Page.class);
      when(priceService.find(filter, pageable)).thenReturn(expected);

      // when
      Page<ActivePrice> result = useCase.find(filter, pageable);

      // then
      assertEquals(expected, result);
    }
  }
}
