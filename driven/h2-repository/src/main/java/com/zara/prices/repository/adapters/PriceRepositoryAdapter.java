package com.zara.prices.repository.adapters;

import com.zara.prices.application.ports.driven.PriceRepositoryPort;
import com.zara.prices.domain.models.values.ActivePrice;
import com.zara.prices.repository.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository repository;

    @Override
    public Optional<ActivePrice> findActivePrice(LocalDateTime date, Long productId, Long brandId) {
        return repository.findActivePrice(date, productId, brandId);
    }
}
