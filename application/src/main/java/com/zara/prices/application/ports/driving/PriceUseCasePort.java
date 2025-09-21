package com.zara.prices.application.ports.driving;

import com.zara.prices.application.exceptions.NotFoundException;
import com.zara.prices.domain.models.values.ActivePrice;

import java.time.LocalDateTime;

public interface PriceUseCasePort {

    ActivePrice getActivePrice(
        LocalDateTime date,
        Long productId,
        Long brandId
    ) throws NotFoundException;

}
