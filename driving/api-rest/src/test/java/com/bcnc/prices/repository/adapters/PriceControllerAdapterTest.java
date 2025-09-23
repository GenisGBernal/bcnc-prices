/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcnc.prices.api.rest.dto.AcceptLanguageENUM;
import com.bcnc.prices.api.rest.dto.GetPricesSortFieldENUM;
import com.bcnc.prices.api.rest.dto.PricePaginatedDTO;
import com.bcnc.prices.api.rest.dto.SortDirENUM;
import com.bcnc.prices.application.exceptions.BadRequestException;
import com.bcnc.prices.application.ports.driving.PriceUseCasePort;
import com.bcnc.prices.controller.adapters.PriceControllerAdapter;
import com.bcnc.prices.controller.mappers.DateTimeControllerMapper;
import com.bcnc.prices.controller.mappers.PaginationControllerMapper;
import com.bcnc.prices.controller.mappers.PriceControllerMapper;
import com.bcnc.prices.domain.filters.PaginationRequest;
import com.bcnc.prices.domain.filters.active_price.ActivePriceFilter;
import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import com.bcnc.prices.domain.models.values.ActivePrice;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class PriceControllerAdapterTest {

  @InjectMocks private PriceControllerAdapter adapter;
  @Mock private PriceUseCasePort priceUseCasePort;
  @Mock private PriceControllerMapper priceControllerMapper;
  @Mock private DateTimeControllerMapper dateTimeControllerMapper;
  @Mock private PaginationControllerMapper paginationControllerMapper;

  @Nested
  class GetActivePrice {
    @Test
    void shouldThrowBadRequestException_whenInvalidStringDateRequest() {
      // given
      String date = "hola";
      Long productId = 23423L;
      Long brandId = 323L;
      Integer page = 1;
      Integer pageSize = 10;
      AcceptLanguageENUM languageENUM = AcceptLanguageENUM.ES_ES;
      SortDirENUM sortDir = SortDirENUM.DESC;
      GetPricesSortFieldENUM sortField = GetPricesSortFieldENUM.PRICE;

      // when
      Executable executable =
          () ->
              adapter.getPrices(
                  date, productId, brandId, languageENUM, page, pageSize, sortDir, sortField);

      // then
      assertThrows(BadRequestException.class, executable);
    }

    @Test
    void shouldReturnActivePriceDTO_whenSuccessfulSearch() {
      // given
      String date = "2025-12-13T13:13:14";
      Long productId = 23423L;
      Long brandId = 323L;
      Integer pageNumber = 1;
      Integer pageSize = 10;
      SortDirENUM sortDir = SortDirENUM.DESC;
      GetPricesSortFieldENUM sortField = GetPricesSortFieldENUM.PRICE;
      AcceptLanguageENUM acceptLanguage = AcceptLanguageENUM.ES_ES;

      LocalDateTime localDateTime = LocalDateTime.now();
      when(dateTimeControllerMapper.toDomain(date)).thenReturn(localDateTime);

      ActivePriceSortFieldEnum mappedSortField = ActivePriceSortFieldEnum.PRICE;
      when(priceControllerMapper.toDomainSortField(sortField)).thenReturn(mappedSortField);

      PaginationRequest<ActivePriceSortFieldEnum> paginationRequest =
          new PaginationRequest<>(pageNumber, pageSize, Sort.Direction.DESC, mappedSortField);
      when(paginationControllerMapper.toRequest(pageNumber, pageSize, sortDir, mappedSortField))
          .thenReturn(paginationRequest);

      Page<ActivePrice> activePrices = mock(Page.class);
      when(priceUseCasePort.find(any(ActivePriceFilter.class), eq(paginationRequest)))
          .thenReturn(activePrices);

      PricePaginatedDTO expectedResponse = mock(PricePaginatedDTO.class);
      when(priceControllerMapper.toPricePaginatedResponse(activePrices))
          .thenReturn(expectedResponse);

      // when
      ResponseEntity<PricePaginatedDTO> result =
          adapter.getPrices(
              date, productId, brandId, acceptLanguage, pageNumber, pageSize, sortDir, sortField);

      // then
      assertEquals(expectedResponse, result.getBody());
    }
  }
}
