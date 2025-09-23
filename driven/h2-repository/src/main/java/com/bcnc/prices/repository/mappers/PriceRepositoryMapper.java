/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.mappers;

import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import com.bcnc.prices.domain.models.values.ActivePrice;
import com.bcnc.prices.repository.models.PriceMO;
import com.bcnc.prices.repository.models.fields.BrandFields;
import com.bcnc.prices.repository.models.fields.PriceFields;
import com.bcnc.prices.repository.models.fields.PriceListFields;
import com.bcnc.prices.repository.models.fields.ProductFields;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceRepositoryMapper {

  @Mapping(target = "productId", source = "productMO.id")
  @Mapping(target = "brandId", source = "brandMO.id")
  @Mapping(target = "priceListId", source = "priceListMO.id")
  ActivePrice toActivePrice(PriceMO priceMO);

  default String toRepositorySortField(ActivePriceSortFieldEnum activePriceSortFieldEnum) {
    return switch (activePriceSortFieldEnum) {
      case PRICE -> PriceFields.PRICE;
      case CURRENCY -> PriceFields.CURRENCY;
      case BRAND_ID -> PriceFields.BRAND + "." + BrandFields.ID;
      case START_DATE -> PriceFields.START_DATE;
      case END_DATE -> PriceFields.END_DATE;
      case PRICE_LIST_ID -> PriceFields.PRICE_LIST + "." + PriceListFields.ID;
      case PRODUCT_ID -> PriceFields.PRODUCT + "." + ProductFields.ID;
    };
  }
}
