package com.zara.prices.application.ports.driven;

import com.zara.prices.domain.models.values.ActivePrice;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {

    Optional<ActivePrice> findActivePrice(
        LocalDateTime date,
        Long productId,
        Long brandId
    );

}
