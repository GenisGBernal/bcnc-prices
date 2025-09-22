package com.zara.prices.application.use_cases;

import com.zara.prices.application.exceptions.NotFoundException;
import com.zara.prices.application.ports.driving.PriceUseCasePort;
import com.zara.prices.application.services.PriceService;
import com.zara.prices.domain.models.values.ActivePrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceUseCase implements PriceUseCasePort {

    private final PriceService service;

    @Transactional(readOnly = true)
    @Override
    public ActivePrice getActivePrice(
        LocalDateTime date,
        Long productId,
        Long brandId
    ) throws NotFoundException {
        return service.findActivePrice(date, productId, brandId)
            .orElseThrow(NotFoundException::new);
    }

}
