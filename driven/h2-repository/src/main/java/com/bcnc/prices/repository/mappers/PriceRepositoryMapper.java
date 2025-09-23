/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.mappers;

import com.bcnc.prices.domain.models.values.ActivePrice;
import com.bcnc.prices.repository.models.PriceMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceRepositoryMapper {

    @Mapping(target = "productId", source = "productMO.id")
    @Mapping(target = "brandId", source = "brandMO.id")
    @Mapping(target = "priceListId", source = "priceListMO.id")
    ActivePrice toActivePrice(PriceMO priceMO);

}
