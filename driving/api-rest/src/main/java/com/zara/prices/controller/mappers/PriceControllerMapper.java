package com.zara.prices.controller.mappers;

import com.zara.prices.api.rest.dto.ActivePriceDTO;
import com.zara.prices.domain.models.values.ActivePrice;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {
        DateTimeControllerMapper.class
    }
)
public interface PriceControllerMapper {

    ActivePriceDTO toResponse(ActivePrice activePrice);

}
