/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.controller.mappers;

import com.bcnc.prices.api.rest.dto.PagePaginationDTO;
import com.bcnc.prices.api.rest.dto.SortDirENUM;
import com.bcnc.prices.controller.configs.PaginationConfig;
import com.bcnc.prices.domain.filters.PaginationRequest;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

@Mapper(componentModel = "spring")
public interface PaginationControllerMapper {

  default <S> PaginationRequest<S> toRequest(
      Integer page, Integer pageSize, SortDirENUM sortDir, S sortField) {
    return PaginationRequest.of(
        page - PaginationConfig.PAGE_OFFSET,
        pageSize,
        Sort.Direction.valueOf(sortDir.getValue()),
        sortField);
  }

  default PagePaginationDTO toResponse(Page<?> page) {
    PagePaginationDTO pageDTO = new PagePaginationDTO();

    pageDTO.currentPageSize(page.getNumberOfElements());
    pageDTO.currentPage(page.getNumber() + PaginationConfig.PAGE_OFFSET);
    pageDTO.hasNextPage(page.hasNext());
    pageDTO.totalItems(page.getTotalElements());
    pageDTO.totalPages((long) page.getTotalPages());

    return pageDTO;
  }
}
