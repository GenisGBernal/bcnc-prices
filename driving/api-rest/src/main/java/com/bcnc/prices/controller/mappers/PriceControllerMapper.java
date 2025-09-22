package com.bcnc.prices.controller.mappers;

import com.bcnc.prices.api.rest.dto.ActivePriceDTO;
import com.bcnc.prices.domain.models.values.ActivePrice;
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
