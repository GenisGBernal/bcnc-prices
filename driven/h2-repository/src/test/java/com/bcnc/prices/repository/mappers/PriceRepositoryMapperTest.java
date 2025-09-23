/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bcnc.prices.domain.models.values.ActivePrice;
import com.bcnc.prices.repository.models.PriceMO;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
}
