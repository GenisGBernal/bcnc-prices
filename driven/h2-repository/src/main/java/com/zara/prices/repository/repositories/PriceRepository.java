package com.zara.prices.repository.repositories;

import com.zara.prices.domain.models.values.ActivePrice;
import com.zara.prices.repository.models.PriceMO;
import com.zara.prices.repository.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriceRepository extends BaseRepository<PriceMO, UUID> {

    @Query("""
        SELECT new com.zara.prices.domain.models.values.ActivePrice(
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
        @Param("brandId") Long brandId
    );

}
