/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bcnc.prices.api.rest.dto.PagePaginationDTO;
import com.bcnc.prices.api.rest.dto.SortDirENUM;
import com.bcnc.prices.controller.configs.PaginationConfig;
import com.bcnc.prices.controller.mappers.PaginationControllerMapper;
import com.bcnc.prices.domain.filters.PaginationRequest;
import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
class PaginationControllerMapperTest {

  private PaginationControllerMapper mapper = Mappers.getMapper(PaginationControllerMapper.class);

  @Test
  void testToRequest() {
    // given
    Integer page = 2;
    Integer pageSize = 49;
    SortDirENUM sortDirENUM = SortDirENUM.ASC;
    Sort.Direction sortDir = Sort.Direction.valueOf(sortDirENUM.getValue());
    ActivePriceSortFieldEnum sortField = ActivePriceSortFieldEnum.PRICE;

    // when
    PaginationRequest<ActivePriceSortFieldEnum> result =
        mapper.toRequest(page, pageSize, sortDirENUM, sortField);
    // then
    assertEquals(page - PaginationConfig.PAGE_OFFSET, result.pageNumber());
    assertEquals(pageSize, result.pageSize());
    assertEquals(sortDir, result.sortDir());
    assertEquals(sortField, result.sortField());
  }

  @Test
  void testToResponsePage() {
    // given
    List<Object> content = Collections.emptyList();
    Pageable pageable = PageRequest.of(2, 20);
    Page<?> base = new PageImpl<>(content, pageable, 20L);

    // when
    PagePaginationDTO result = mapper.toResponse(base);

    // then
    assertEquals(0, result.getCurrentPageSize());
    assertEquals(2 + PaginationConfig.PAGE_OFFSET, result.getCurrentPage());
    assertEquals(false, result.getHasNextPage()); // 3rd page of 5 (100/20=5)
    assertEquals(20, result.getTotalItems());
    assertEquals(1L, result.getTotalPages());
  }
}
