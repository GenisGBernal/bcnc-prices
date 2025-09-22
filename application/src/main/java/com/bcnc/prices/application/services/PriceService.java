package com.bcnc.prices.application.services;

import com.bcnc.prices.application.ports.driven.PriceRepositoryPort;
import com.bcnc.prices.domain.models.values.ActivePrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepositoryPort repositoryPort;

    // If maximum performance was needed, an in-memory or shared cache could be used here.
    // But as the query params include a date, hits would probably be low.
    public Optional<ActivePrice> findActivePrice(
        LocalDateTime date,
        Long productId,
        Long brandId
    ) {
        return repositoryPort.findActivePrice(date, productId, brandId);
    }

}
