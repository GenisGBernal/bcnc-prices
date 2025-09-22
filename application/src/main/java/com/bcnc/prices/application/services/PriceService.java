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

    public Optional<ActivePrice> findActivePrice(
        LocalDateTime date,
        Long productId,
        Long brandId
    ) {
        return repositoryPort.findActivePrice(date, productId, brandId);
    }

}
