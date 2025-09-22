package com.bcnc.prices.application.use_cases;

import com.bcnc.prices.application.exceptions.NotFoundException;
import com.bcnc.prices.application.ports.driving.PriceUseCasePort;
import com.bcnc.prices.application.services.PriceService;
import com.bcnc.prices.domain.models.values.ActivePrice;
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
