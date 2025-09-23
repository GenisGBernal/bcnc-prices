/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.controller.mappers;

import com.bcnc.prices.api.rest.dto.GetPricesSortFieldENUM;
import com.bcnc.prices.api.rest.dto.PriceDTO;
import com.bcnc.prices.api.rest.dto.PricePaginatedDTO;
import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import com.bcnc.prices.domain.models.values.ActivePrice;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(
    componentModel = "spring",
    uses = {DateTimeControllerMapper.class, PaginationControllerMapper.class})
public interface PriceControllerMapper {

  PriceDTO toResponse(ActivePrice activePrice);

  List<PriceDTO> toResponseFromActivePrices(List<ActivePrice> activePrice);

  @Mapping(target = "pagination", source = ".")
  @Mapping(target = "items", source = "content")
  PricePaginatedDTO toPricePaginatedResponse(Page<ActivePrice> activePrices);

  ActivePriceSortFieldEnum toDomainSortField(GetPricesSortFieldENUM sortField);
}
