/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.use_cases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcnc.prices.application.services.PriceService;
import com.bcnc.prices.application.use_cases.PriceUseCase;
import com.bcnc.prices.domain.filters.PaginationRequest;
import com.bcnc.prices.domain.filters.active_price.ActivePriceFilter;
import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import com.bcnc.prices.domain.models.values.ActivePrice;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

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
      PaginationRequest<ActivePriceSortFieldEnum> pageable = mock(PaginationRequest.class);

      Page<ActivePrice> expected = mock(Page.class);
      when(priceService.find(filter, pageable)).thenReturn(expected);

      // when
      Page<ActivePrice> result = useCase.find(filter, pageable);

      // then
      assertEquals(expected, result);
    }
  }
}
