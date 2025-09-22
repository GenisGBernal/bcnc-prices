package com.bcnc.prices.application.ports.driving;

import com.bcnc.prices.application.exceptions.NotFoundException;
import com.bcnc.prices.domain.models.values.ActivePrice;

import java.time.LocalDateTime;

public interface PriceUseCasePort {

    ActivePrice getActivePrice(
        LocalDateTime date,
        Long productId,
        Long brandId
    ) throws NotFoundException;

}
