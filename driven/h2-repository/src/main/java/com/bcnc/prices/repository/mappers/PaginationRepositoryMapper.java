/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.mappers;

import com.bcnc.prices.domain.filters.PaginationRequest;
import org.mapstruct.Mapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Mapper(componentModel = "spring")
public interface PaginationRepositoryMapper {

  default PageRequest toPageRequest(PaginationRequest<?> paginationRequest, String sortField) {
    return PageRequest.of(
        paginationRequest.pageNumber(),
        paginationRequest.pageSize(),
        Sort.by(paginationRequest.sortDir(), sortField));
  }
}
