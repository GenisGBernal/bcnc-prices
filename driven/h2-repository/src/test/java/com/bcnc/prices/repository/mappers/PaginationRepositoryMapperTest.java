/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bcnc.prices.domain.filters.PaginationRequest;
import com.bcnc.prices.repository.models.fields.PriceFields;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
public class PaginationRepositoryMapperTest {

  private PaginationRepositoryMapper mapper = Mappers.getMapper(PaginationRepositoryMapper.class);

  @Test
  void shouldMapToPageRequestWhenValidPaginationRequest() {
    // given
    int pageNumber = 1;
    int pageSize = 20;
    Sort.Direction sortDirection = Sort.Direction.DESC;
    String sortField = PriceFields.PRICE;

    PaginationRequest<Void> paginationRequest =
        new PaginationRequest<>(pageNumber, pageSize, sortDirection, null);

    // when
    PageRequest result = mapper.toPageRequest(paginationRequest, sortField);

    // then
    assertEquals(pageNumber, result.getPageNumber());
    assertEquals(pageSize, result.getPageSize());
    assertEquals(sortDirection, result.getSort().getOrderFor(sortField).getDirection());
    assertEquals(sortField, result.getSort().getOrderFor(sortField).getProperty());
  }
}
