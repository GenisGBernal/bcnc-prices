/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.adapters;

import com.bcnc.prices.application.ports.driven.PriceRepositoryPort;
import com.bcnc.prices.domain.filters.PaginationRequest;
import com.bcnc.prices.domain.filters.active_price.ActivePriceFilter;
import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import com.bcnc.prices.domain.models.values.ActivePrice;
import com.bcnc.prices.repository.mappers.PaginationRepositoryMapper;
import com.bcnc.prices.repository.mappers.PriceRepositoryMapper;
import com.bcnc.prices.repository.repositories.PriceRepository;
import com.bcnc.prices.repository.specifications.ActivePriceSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

  private final PriceRepository repository;
  private final PriceRepositoryMapper mapper;
  private final PaginationRepositoryMapper paginationRepositoryMapper;

  public Page<ActivePrice> find(
      ActivePriceFilter filter, PaginationRequest<ActivePriceSortFieldEnum> pageable) {
    ActivePriceSpecification specification = ActivePriceSpecification.of(filter);
    String activePriceSortField = mapper.toRepositorySortField(pageable.sortField());
    PageRequest pageRequest =
        paginationRepositoryMapper.toPageRequest(pageable, activePriceSortField);

    return repository.findAll(specification, pageRequest).map(mapper::toActivePrice);
  }
}
