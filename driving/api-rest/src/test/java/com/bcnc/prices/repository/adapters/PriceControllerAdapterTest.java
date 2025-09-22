/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcnc.prices.api.rest.dto.ActivePriceDTO;
import com.bcnc.prices.application.exceptions.BadRequestException;
import com.bcnc.prices.application.ports.driving.PriceUseCasePort;
import com.bcnc.prices.controller.adapters.PriceControllerAdapter;
import com.bcnc.prices.controller.mappers.DateTimeControllerMapper;
import com.bcnc.prices.controller.mappers.PriceControllerMapper;
import com.bcnc.prices.domain.models.values.ActivePrice;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class PriceControllerAdapterTest {

  @InjectMocks private PriceControllerAdapter adapter;
  @Mock private PriceUseCasePort priceUseCasePort;
  @Mock private PriceControllerMapper priceControllerMapper;
  @Mock private DateTimeControllerMapper dateTimeControllerMapper;

  @Nested
  class GetActivePrice {
    @Test
    void shouldThrowBadRequestException_whenInvalidStringDateRequest() {
      // given
      String date = "hola";
      Long productId = 23423L;
      Long brandId = 323L;

      when(dateTimeControllerMapper.toDomain(date)).thenThrow(DateTimeParseException.class);

      // when
      Executable executable = () -> adapter.getActivePrice(date, productId, brandId);

      // then
      assertThrows(BadRequestException.class, executable);
    }

    @Test
    void shouldReturnActivePriceDTO_whenSuccessfulSearch() {
      // given
      String date = "2025-13-13T13:13:14";
      Long productId = 23423L;
      Long brandId = 323L;

      LocalDateTime localDateTime = LocalDateTime.now();
      when(dateTimeControllerMapper.toDomain(date)).thenReturn(localDateTime);

      ActivePrice activePrice = mock(ActivePrice.class);
      when(priceUseCasePort.getActivePrice(localDateTime, productId, brandId))
          .thenReturn(activePrice);

      ActivePriceDTO expectedResponse = mock(ActivePriceDTO.class);
      when(priceControllerMapper.toResponse(activePrice)).thenReturn(expectedResponse);

      // when
      ResponseEntity<ActivePriceDTO> result = adapter.getActivePrice(date, productId, brandId);

      // then
      assertEquals(expectedResponse, result.getBody());
    }
  }
}
