package com.zara.prices.repository.adapters;

import com.zara.prices.application.ports.driven.PriceRepositoryPort;
import com.zara.prices.repository.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository repository;

}
