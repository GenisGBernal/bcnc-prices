/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.controller.adapters;

import com.bcnc.prices.api.rest.PricesApi;
import com.bcnc.prices.api.rest.dto.AcceptLanguageENUM;
import com.bcnc.prices.api.rest.dto.GetPricesSortFieldENUM;
import com.bcnc.prices.api.rest.dto.PricePaginatedDTO;
import com.bcnc.prices.api.rest.dto.SortDirENUM;
import com.bcnc.prices.application.exceptions.BadRequestException;
import com.bcnc.prices.application.ports.driving.PriceUseCasePort;
import com.bcnc.prices.controller.mappers.DateTimeControllerMapper;
import com.bcnc.prices.controller.mappers.PaginationControllerMapper;
import com.bcnc.prices.controller.mappers.PriceControllerMapper;
import com.bcnc.prices.controller.utils.MessageKeys;
import com.bcnc.prices.controller.utils.ValidatorUtils;
import com.bcnc.prices.domain.filters.PaginationRequest;
import com.bcnc.prices.domain.filters.active_price.ActivePriceFilter;
import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import com.bcnc.prices.domain.models.values.ActivePrice;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
  public ResponseEntity<PricePaginatedDTO> getPrices(
      String date,
      Long productId,
      Long brandId,
      AcceptLanguageENUM acceptLanguage,
      Integer pageNumber,
      Integer pageSize,
      SortDirENUM sortDir,
      GetPricesSortFieldENUM sortField) {
    if (!ValidatorUtils.validateString(date))
      throw new BadRequestException(MessageKeys.EXCEPTION_GET_PRICES_BAD_REQUEST_DATE);

    LocalDateTime dateReq = dateTimeControllerMapper.toDomain(date);
    ActivePriceFilter filter = new ActivePriceFilter(dateReq, productId, brandId);
    ActivePriceSortFieldEnum sortFieldEnum = mapper.toDomainSortField(sortField);
    PaginationRequest<ActivePriceSortFieldEnum> pageable =
        paginationControllerMapper.toRequest(pageNumber, pageSize, sortDir, sortFieldEnum);

    Page<ActivePrice> activePrices = useCasePort.find(filter, pageable);

    PricePaginatedDTO response = mapper.toPricePaginatedResponse(activePrices);
    return ResponseEntity.ok(response);
  }
}
