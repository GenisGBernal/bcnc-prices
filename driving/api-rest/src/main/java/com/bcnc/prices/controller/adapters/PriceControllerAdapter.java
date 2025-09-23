/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.controller.adapters;

import com.bcnc.prices.api.rest.PricesApi;
import com.bcnc.prices.api.rest.dto.PricePaginatedDTO;
import com.bcnc.prices.application.exceptions.BadRequestException;
import com.bcnc.prices.application.ports.driving.PriceUseCasePort;
import com.bcnc.prices.controller.mappers.DateTimeControllerMapper;
import com.bcnc.prices.controller.mappers.PaginationControllerMapper;
import com.bcnc.prices.controller.mappers.PriceControllerMapper;
import com.bcnc.prices.controller.utils.MessageKeys;
import com.bcnc.prices.controller.utils.ValidatorUtils;
import com.bcnc.prices.domain.filters.ActivePriceFilter;
import com.bcnc.prices.domain.models.values.ActivePrice;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceControllerAdapter implements PricesApi {

  private final PriceUseCasePort useCasePort;
  private final PriceControllerMapper mapper;
  private final DateTimeControllerMapper dateTimeControllerMapper;
  private final PaginationControllerMapper paginationControllerMapper;

  @Override
  public ResponseEntity<PricePaginatedDTO> getPrices(String date, Long productId, Long brandId, Integer page, Integer pageSize) {
    if (!ValidatorUtils.validateString(date)) throw new BadRequestException(MessageKeys.EXCEPTION_GET_PRICES_BAD_REQUEST_DATE);

    LocalDateTime dateReq = dateTimeControllerMapper.toDomain(date);
    ActivePriceFilter filter = new ActivePriceFilter(dateReq, productId, brandId);
    Pageable pageable = paginationControllerMapper.toRequest(page, pageSize);

    Page<ActivePrice> activePrices = useCasePort.find(filter, pageable);

    PricePaginatedDTO response = mapper.toPricePaginatedResponse(activePrices);
    return ResponseEntity.ok(response);
  }
}
