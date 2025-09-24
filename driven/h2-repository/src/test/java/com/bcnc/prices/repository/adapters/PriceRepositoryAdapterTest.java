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

import com.bcnc.prices.domain.filters.PaginationRequest;
import com.bcnc.prices.domain.filters.active_price.ActivePriceFilter;
import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import com.bcnc.prices.domain.models.values.ActivePrice;
import com.bcnc.prices.repository.mappers.PaginationRepositoryMapper;
import com.bcnc.prices.repository.mappers.PriceRepositoryMapper;
import com.bcnc.prices.repository.models.PriceMO;
import com.bcnc.prices.repository.repositories.PriceRepository;
import com.bcnc.prices.repository.specifications.ActivePriceSpecification;
import java.util.function.Function;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
public class PriceRepositoryAdapterTest {

  @InjectMocks private PriceRepositoryAdapter adapter;
  @Mock private PriceRepository priceRepository;
  @Mock private PriceRepositoryMapper priceRepositoryMapper;
  @Mock private PaginationRepositoryMapper paginationRepositoryMapper;

  @Nested
  class FindActivePrice {

    @Test
    void shouldForwardOptional_whenRepositoryReturnsValue() {
      // given
      ActivePriceFilter filter = mock(ActivePriceFilter.class);
      PaginationRequest<ActivePriceSortFieldEnum> pageRequest = mock(PaginationRequest.class);
      ActivePriceSortFieldEnum sortFieldEnum = ActivePriceSortFieldEnum.PRICE;
      when(pageRequest.sortField()).thenReturn(sortFieldEnum);

      String sortField = "sortField";
      when(priceRepositoryMapper.toRepositorySortField(sortFieldEnum)).thenReturn(sortField);

      PageRequest pageable = mock(PageRequest.class);
      when(paginationRepositoryMapper.toPageRequest(pageRequest, sortField)).thenReturn(pageable);

      Page<PriceMO> findAllResult = mock(Page.class);
      when(priceRepository.findAll(any(ActivePriceSpecification.class), eq(pageable)))
          .thenReturn(findAllResult);

      Page<ActivePrice> expected = mock(Page.class);
      when(findAllResult.map(any(Function.class))).thenReturn(expected);

      // when
      Page<ActivePrice> result = adapter.find(filter, pageRequest);

      // then
      assertEquals(expected, result);
    }
  }
}
