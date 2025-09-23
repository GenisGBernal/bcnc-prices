/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcnc.prices.api.rest.dto.PagePaginationDTO;
import com.bcnc.prices.api.rest.dto.PricePaginatedDTO;
import com.bcnc.prices.api.rest.dto.PriceDTO;
import com.bcnc.prices.controller.mappers.DateTimeControllerMapper;
import com.bcnc.prices.controller.mappers.PaginationControllerMapper;
import com.bcnc.prices.controller.mappers.PriceControllerMapper;
import com.bcnc.prices.domain.models.values.ActivePrice;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PriceControllerMapperTest {

  @Spy @InjectMocks
  private PriceControllerMapper mapper = Mappers.getMapper(PriceControllerMapper.class);

  @Mock private DateTimeControllerMapper stringControllerMapper;
  @Mock private PaginationControllerMapper paginationControllerMapper;

  @Test
  void toResponse_shouldReturnPriceDTO_whenInputValidActivePrice() {
    // given
    ActivePrice base = Instancio.create(ActivePrice.class);

    String expectedStartDate = "2025-10-10T10:10:10";
    when(stringControllerMapper.toResponse(base.startDate())).thenReturn(expectedStartDate);

    String expectedEndDate = "2025-13-13T13:13:14";
    when(stringControllerMapper.toResponse(base.endDate())).thenReturn(expectedEndDate);

    // when
    PriceDTO result = mapper.toResponse(base);

    // then
    assertEquals(base.brandId(), result.getBrandId());
    assertEquals(expectedStartDate, result.getStartDate());
    assertEquals(expectedEndDate, result.getEndDate());
    assertEquals(base.priceListId(), result.getPriceListId());
    assertEquals(base.productId(), result.getProductId());
    assertEquals(base.currency(), result.getCurrency());
  }

  @Test
  void toPricePaginatedResponse_shouldReturnPricePaginatedDTO_whenInputValidPageActivePrice() {
    // given
    Page<ActivePrice> base = mock(Page.class);
    when(base.hasContent())
        .thenReturn(true);

    List<ActivePrice> baseActivePrice = List.of(mock(ActivePrice.class));
    when(base.getContent())
        .thenReturn(baseActivePrice);

    List<PriceDTO> expectedDTO = List.of(mock(PriceDTO.class));
    doReturn(expectedDTO)
        .when(mapper).toResponseFromActivePrices(baseActivePrice);

    PagePaginationDTO expectedPagePaginationDTO = mock(PagePaginationDTO.class);
    when(paginationControllerMapper.toResponse(base))
        .thenReturn(expectedPagePaginationDTO);

    // when
    PricePaginatedDTO result = mapper.toPricePaginatedResponse(base);

    // then
    assertEquals(expectedDTO, result.getItems());
    assertEquals(expectedPagePaginationDTO, result.getPagination());
  }
}
