/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.repository.repositories;

import com.bcnc.prices.domain.models.values.ActivePrice;
import com.bcnc.prices.repository.models.PriceMO;
import com.bcnc.prices.repository.repositories.base.BaseRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends BaseRepository<PriceMO, UUID> {

  @Query(
      """
        SELECT new com.bcnc.prices.domain.models.values.ActivePrice(
            p.brandMO.id,
            p.startDate,
            p.endDate,
            p.priceListMO.id,
            p.productMO.id,
            p.price,
            p.currency)
        FROM PriceMO p
        WHERE p.productMO.id = :productId
          AND p.brandMO.id = :brandId
          AND :date BETWEEN p.startDate AND p.endDate
        ORDER BY p.priority DESC
        LIMIT 1""")
  Optional<ActivePrice> findActivePrice(
      @Param("date") LocalDateTime date,
      @Param("productId") Long productId,
      @Param("brandId") Long brandId);
}
