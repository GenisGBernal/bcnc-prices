package com.bcnc.prices.controller.mappers;

import com.bcnc.prices.api.rest.dto.PagePaginationDTO;
import com.bcnc.prices.controller.configs.PaginationConfig;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Mapper(componentModel = "spring")
public interface PaginationControllerMapper {

  default PageRequest toRequest(Integer page, Integer pageSize) {
    return PageRequest.of(
      page - PaginationConfig.PAGE_OFFSET,
      pageSize
    );
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
