/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bcnc.prices.domain.filters.active_price.ActivePriceSortFieldEnum;
import com.bcnc.prices.domain.models.values.ActivePrice;
import com.bcnc.prices.repository.models.PriceMO;
import com.bcnc.prices.repository.models.fields.BrandFields;
import com.bcnc.prices.repository.models.fields.PriceFields;
import com.bcnc.prices.repository.models.fields.PriceListFields;
import com.bcnc.prices.repository.models.fields.ProductFields;
import java.util.stream.Stream;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceRepositoryMapperTest {

  private PriceRepositoryMapper mapper = Mappers.getMapper(PriceRepositoryMapper.class);

  @Test
  void shouldMapActivePriceWhenValidPriceMO() {
    // given
    PriceMO base = Instancio.create(PriceMO.class);

    // when
    ActivePrice result = mapper.toActivePrice(base);

    // then
    assertEquals(base.getProductMO().getId(), result.productId());
    assertEquals(base.getBrandMO().getId(), result.brandId());
    assertEquals(base.getPriceListMO().getId(), result.priceListId());
    assertEquals(base.getStartDate(), result.startDate());
    assertEquals(base.getEndDate(), result.endDate());
    assertEquals(base.getPrice(), result.price());
    assertEquals(base.getCurrency(), result.currency());
  }

  @ParameterizedTest
  @MethodSource("provideSortFieldMappings")
  void shouldMapEnumToRepositorySortField(
      ActivePriceSortFieldEnum enumValue, String expectedField) {
    // when
    String result = mapper.toRepositorySortField(enumValue);

    // then
    assertEquals(expectedField, result);
  }

  private static Stream<Arguments> provideSortFieldMappings() {
    return Stream.of(
        Arguments.of(ActivePriceSortFieldEnum.PRICE, PriceFields.PRICE),
        Arguments.of(ActivePriceSortFieldEnum.CURRENCY, PriceFields.CURRENCY),
        Arguments.of(ActivePriceSortFieldEnum.BRAND_ID, PriceFields.BRAND + "." + BrandFields.ID),
        Arguments.of(ActivePriceSortFieldEnum.START_DATE, PriceFields.START_DATE),
        Arguments.of(ActivePriceSortFieldEnum.END_DATE, PriceFields.END_DATE),
        Arguments.of(
            ActivePriceSortFieldEnum.PRICE_LIST_ID,
            PriceFields.PRICE_LIST + "." + PriceListFields.ID),
        Arguments.of(
            ActivePriceSortFieldEnum.PRODUCT_ID, PriceFields.PRODUCT + "." + ProductFields.ID));
  }
}
